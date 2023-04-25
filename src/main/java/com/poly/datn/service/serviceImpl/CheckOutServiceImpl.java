package com.poly.datn.service.serviceImpl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.poly.datn.common.mapper.ModelConverter;
import com.poly.datn.dto.request.CheckOutRequest;
import com.poly.datn.dto.response.PaymentMethodResponse;
import com.poly.datn.entity.Cart;
import com.poly.datn.entity.CartDetail;
import com.poly.datn.entity.Notification;
import com.poly.datn.entity.Order;
import com.poly.datn.entity.Order.OrderBuilder;
import com.poly.datn.entity.OrderDetail;
import com.poly.datn.entity.OrderStatus;
import com.poly.datn.entity.PaymentMethod;
import com.poly.datn.entity.PromotionUser;
import com.poly.datn.entity.User;
import com.poly.datn.exception.InventoryException;
import com.poly.datn.exception.cart.CartException;
import com.poly.datn.repository.AccountRepository;
import com.poly.datn.repository.NotificationRepository;
import com.poly.datn.repository.OrderRepository;
import com.poly.datn.repository.OrderStatusRepository;
import com.poly.datn.repository.PaymentMethodRepository;
import com.poly.datn.repository.PromotionUserRepository;
import com.poly.datn.repository.UserRepository;
import com.poly.datn.service.CartService;
import com.poly.datn.service.CheckOutService;
import com.poly.datn.service.MailService;
import com.poly.datn.service.UserInfoByTokenService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
@Service
@Slf4j
@Transactional(rollbackFor = RuntimeException.class, noRollbackFor = {IllegalArgumentException.class,InventoryException.class})
@RequiredArgsConstructor
public class CheckOutServiceImpl implements CheckOutService {

    final CartService cartService;
    final PaymentMethodRepository paymentRepo;
    final PromotionUserRepository promtionUserRepo;
    final OrderStatusRepository orderStatusRepo;
    final OrderRepository orderRepository;
    final ModelConverter modelConverter;
    final SimpMessageSendingOperations messagingTemplate;
    final AccountRepository accountRepository;
    final UserRepository userRepository;
    final MailService mailService;
    final UserInfoByTokenService userInfoService;
    final NotificationRepository notificationRepository;
    final PaymentMethodRepository paymentMethodRepository;


    @Override
    public Integer checkout(Integer userId, CheckOutRequest request) throws InventoryException{
        int saved = -1;
        try {
            Cart userCart = validateCartOfUser(userId);
          
            Set<CartDetail> cartDetails = userCart.getCartDetails();
            Set<CartDetail> reduce = new HashSet<>();
          
            if(cartDetails.isEmpty() || (!cartDetails.isEmpty() && cartDetailAllOutofStock(cartDetails))) {
                throw new IllegalArgumentException("Giỏ hàng trống, không thể thanh toán");
            }

            boolean variantStatusFlag = cartService.updateCartByVariantStatus();
            if(variantStatusFlag) throw new IllegalArgumentException("Lỗi sản phẩm trong giỏ cập nhật, không thể thanh toán");
          
           
            Set<CartDetail> cloneSet = new HashSet<>();

            cartDetails.forEach(c -> cloneSet.add(c.clone()));

            boolean inventoryFlag = cartService.updateCartByInventory();

            // default all cartdetails to orderdetails
            Set<OrderDetail> orderDetails = mapAllByIterator(cartDetails);
            
            // update cart check inventory before checkout
            if(inventoryFlag) {
                cartService.refresh(userCart);
                Set<CartDetail> fresh =   cartService.findCartEntityByUserId(userId).getCartDetails();

                // fresh.forEach((e) -> System.out.println("fresh cart-d: " + e.getId() + "qu: " + e.getQuantity()));
                
                reduce = compareUpdated(fresh, cloneSet);
              
                //  cloneSet.forEach(e -> System.out.println(e.getId() + "clone list - q: " + e.getQuantity()));

                //  reduce.forEach(e -> System.out.println(e.getId() +  "reduce list - q: " + e.getQuantity()));

                // change orderdetails after check inventory between current cart and checked out request cart
    
                 orderDetails = mapAllByIterator(reduce);
            }

            Order newOrder = buildOrder(userCart, request);

            // System.out.println("*******************************");
            // orderDetails.forEach(e -> System.out.println(e.getProductVariant().getId() +": " + e.getQuantity()));
            // Set<OrderDetail> orderDetails = mapAllByIterator(cartDetails);

            // removed out stock cart detail;
            boolean removed = orderDetails.removeIf(e-> e.getQuantity() == 0);

            // orderDetails.forEach(e -> System.out.println("added to order: " + e.getProductVariant().getId() +": " + e.getQuantity() + "price_sum: "  + e.getPriceSum()));
            log.info("removed");
            newOrder.setOrderDetails(orderDetails);
            log.info("saved order");

            saved = orderRepository.save(newOrder).getId();

            if (saved > 0)
            log.info("removed items");
            // cartService.deleteAllItemsInCart(userCart.getId());
            if(reduce.size() > 0) {
                // ? remove requested cart detail only (before updated)
                log.info("removed only requested cart-detail");
                cartService.deleteAllWithIdIn(userCart.getId(), getCartIdsList(reduce));
            }else {
                // ? cart is ok, remove all cart detail;
                log.info("cart is not updated -> removed all from cart-detail");
                cartService.deleteAllWithIdIn(userCart.getId(),getCartIdsList(cartDetails));
            }    
            log.info("updated  price sum..." + "current cart size: " +  userCart.getCartDetails().size());  
            cartService.updatedPriceSum(userCart.getId());


            log.info("Calling mail service...");
            User currentUser = userInfoService.getCurrentUser();
            mailService.sendEmailThankLetter(currentUser.getFullName(), currentUser.getEmail());
            Notification notification = new Notification();
            notification.setHeading("Thông báo đơn hàng");
            notification.setSubtitle("Số lượng sản phẩm: "+ orderDetails.size());
            notification.setPath("order");
            notification.setTitle("Khách hàng " + currentUser.getFullName() + " đã đặt hàng!");
            notificationRepository.save(notification);
            this.messagingTemplate.convertAndSend("/topic/server", "Khách hàng " + currentUser.getFullName()+ " đã đặt hàng!");

        } catch (Exception ex) {
            ex.printStackTrace();
            if (ex instanceof CartException) {
                throw new RuntimeException(((CartException) ex).getMessage());
            } else if(ex instanceof IllegalArgumentException) {
                throw new IllegalArgumentException(ex.getMessage());
            }else if(ex instanceof InventoryException){
                throw new InventoryException(ex.getMessage());
            }else {
                throw ex;
            }
        }
        return saved;
    }

    public Order buildOrder(Cart cart, CheckOutRequest request) {
        try {
            OrderBuilder builder = Order.builder();

            User user = cart.getUser();
            log.info("get payment_id: " + request.getPayment_method_id());
            PaymentMethod paymentMethod = paymentRepo.findById(request.getPayment_method_id()).get();
            boolean isPay = false;

            String MOMO = "momo", VISA_CARD = "VISA_CARD";
            if(paymentMethod.getMethod().equalsIgnoreCase(MOMO) || paymentMethod.getMethod().equalsIgnoreCase(VISA_CARD)) {
                isPay = true;
            }
            log.info("GET METHOD: " +  paymentMethod.getMethod());
            PromotionUser promotion = promtionUserRepo.findById(request.getPromotionUser_id()).orElse(null);
            // Data not available
            OrderStatus orderStatus = orderStatusRepo.findById(1).get();

            return builder.withId(0)
                    .withAddressLine(request.getAddressLine())
                    .withProvince(request.getProvince())
                    .withDistrict(request.getDistrict())
                    .withPostalId(request.getPostalId())
                    .withPayment(paymentMethod)
                    .withPromotion(promotion)
                    .withStatus(orderStatus)
                    .withIsPay(isPay)
                    .withUser(user).build();
        } catch (Exception ex) {
            log.info("buildOrder error");
            ex.printStackTrace();
            throw new RuntimeException("Can't create order");
        }
        // return null;

    }

    public Cart validateCartOfUser(Integer userId) {
        cartService.existsByUserId(userId, true);
        return cartService.findCartEntityByUserId(userId);
    }

    private Set<OrderDetail> mapAllByIterator(Set<CartDetail> cartDetails) {
        try {
            Set<OrderDetail> setOrderDetail = cartDetails.stream().map((e) -> checkoutMapCartDetailToOrderDetail(e))
                    .collect(Collectors.toCollection(HashSet::new));
            return setOrderDetail;

        } catch (Exception e) {
            log.warn("mapAllByIterator error");
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
    }

    public OrderDetail checkoutMapCartDetailToOrderDetail(CartDetail cartDetail) {
        try {
            OrderDetail order = modelConverter.getTypeMap(CartDetail.class, OrderDetail.class).addMappings(mapper -> {
                mapper.skip(OrderDetail::setRating);
                mapper.skip(OrderDetail::setId);
                mapper.map(CartDetail::getQuantity, OrderDetail::setQuantity);
                mapper.map(CartDetail::getProductVariant, OrderDetail::setProductVariant);
                mapper.map(CartDetail::getPrice_Detail, OrderDetail::setPriceSum);
                // mapper.<Double>map(s -> { return (Double)(s.getDiscount_Amount() * s.getQuantity());}, (destination, value) ->  destination.setPromotionValue(value));
                mapper.map(CartDetail::getDiscount_Amount, OrderDetail::setPromotionValue);
            }).map(cartDetail);
            // ? debug clean later
            // log.info("order: " + order.getPriceSum() + " -  " + order.getProductVariant().getId() + " - "
            //         + order.getQuantity());
            return order;
        } catch (Exception e) {
            e.printStackTrace();
            log.info(" checkoutMapCartDetailToOrderDetail error");
            throw new RuntimeException("checkoutMapCartDetailToOrderDetail error");
        }

    }

    @Override
    public List<PaymentMethodResponse> getPaymentMethod() {
       return  modelConverter.mapAllByIterator( paymentMethodRepository.findAll(), PaymentMethodResponse.class);
    }

    public Set<CartDetail> compareUpdated(Set<CartDetail> fresh, Set<CartDetail> clone) throws InventoryException{

        List<CartDetail> freshList = new ArrayList<>(fresh);
        List<CartDetail> cloneList = new ArrayList<>(clone);
        // filter all quantity zero in request cart
        List<CartDetail> quantityZero = cloneList.stream().filter(t -> t.getQuantity() == 0).collect(Collectors.toList());

        // log.info("quantity zeror size: " + quantityZero.size());
        freshList.forEach(od -> {
           int curQty = od.getQuantity();
           if(curQty == 0 && !quantityZero.contains(od)) {
              log.error("throw　error: Current inventory is 0 but order request quantiy > 0");
              throw new InventoryException("Số lượng giỏ hàng thay đổi, sản phẩm hết hàng, vui lòng thanh toán lại sau");
           }else if(curQty > 0 && quantityZero.contains(od)) {
            log.info("quantity plus 1 but request none");
            // quantity plus and not in order
                int index = cloneList.indexOf(od);
                // log.info("remove orderDetail in clone list with index: " + index);
                cloneList.remove(index);
           }else if(curQty > 0 && !quantityZero.contains(od)) {
               int index =  cloneList.indexOf(od);
               int reQty = cloneList.get(index).getQuantity();
               if(curQty < reQty) {
                log.error("throw　error: mismatch quantity (current inventory < order request quantiyt)");
                throw new  InventoryException("Số lượng giỏ hàng thay đổi, không đủ sản phẩm cho đơn hàng, vui lòng thử lại sau");
               }
           }
        });
        // System.out.println("-------------------------------\n");
       
        // freshList.forEach((e) -> System.out.println("fresh cart-d: " + e.getId() + "qu: " + e.getQuantity()));
              
        // cloneList.forEach(e -> System.out.println(e.getId() + "clone list - q: " + e.getQuantity()));

        // System.out.println("-------------------------------\n");

        if(cloneList.size() < freshList.size()) {
            log.info("cart is updated(out-stock cart-detail is updated to 1) so cart ok to checkout: ");
            return new HashSet<>(cloneList) ;
        }
        log.info("Return updated cart after compareUpdated()");
        return fresh;
    }

    public boolean cartDetailAllOutofStock(Set<CartDetail> cartDetails) {
       long allZero = cartDetails.stream().filter(i-> i.getQuantity() > 0).count();
        return allZero == 0;
    }

    public List<Integer> getCartIdsList(Set<CartDetail> cartDetails) {
        Stream<CartDetail> stream = cartDetails.stream().filter(e -> e.getQuantity() > 0);
        List<Integer> ids = stream.map(e -> e.getId()).collect(Collectors.toList());
        return ids;
    }
    
}
