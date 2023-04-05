package com.poly.datn.service.serviceImpl;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.poly.datn.common.mapper.ModelConverter;
import com.poly.datn.dto.request.CheckOutRequest;
import com.poly.datn.entity.Account;
import com.poly.datn.entity.Cart;
import com.poly.datn.entity.CartDetail;
import com.poly.datn.entity.Order;
import com.poly.datn.entity.Order.OrderBuilder;
import com.poly.datn.entity.OrderDetail;
import com.poly.datn.entity.OrderStatus;
import com.poly.datn.entity.PaymentMethod;
import com.poly.datn.entity.PromotionUser;
import com.poly.datn.entity.User;
import com.poly.datn.exception.cart.CartException;
import com.poly.datn.repository.AccountRepository;
import com.poly.datn.repository.OrderRepository;
import com.poly.datn.repository.OrderStatusRepository;
import com.poly.datn.repository.PaymentMethodRepository;
import com.poly.datn.repository.PromotionUserRepository;
import com.poly.datn.repository.UserRepository;
import com.poly.datn.security.UserPrincipal;
import com.poly.datn.service.CartService;
import com.poly.datn.service.CheckOutService;
import com.poly.datn.service.MailService;
import com.poly.datn.service.UserInfoByTokenService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@Transactional(rollbackFor = Exception.class)
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
    @Override
    public Integer checkout(Integer userId, CheckOutRequest request) {
        int saved = -1;
        try {
            Cart userCart = validateCartOfUser(userId);
            Set<CartDetail> cartDetails = userCart.getCartDetails();

            Order newOrder = buildOrder(userCart, request);

            Set<OrderDetail> orderDetails = mapAllByIterator(cartDetails);
            newOrder.setOrderDetails(orderDetails);
            log.info("saved order");
            saved = orderRepository.save(newOrder).getId();
            
            log.info("Calling mail service...");
            // UserPrincipal userPrincipal = (UserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            // Account account = accountRepository.findByUsername(userPrincipal.getUsername());
            // mailService.sendEmailThankLetter(account.getUser().getFullName(), account.getUser().getEmail());
            User currentUser = userInfoService.getCurrentUser();
            mailService.sendEmailThankLetter(currentUser.getFullName(), currentUser.getEmail());
            this.messagingTemplate.convertAndSend("/topic/server", "Khách hàng " + currentUser.getFullName()+ " đã đặt hàng thành công!");
           
            if (saved > 0)
            log.info("removed items");
            cartService.deleteAllItemsInCart(userCart.getId());
           
            log.info("updated  price sum..." + "current cart size: " +  userCart.getCartDetails().size());
             
            cartService.updatedPriceSum(userCart.getId());
        } catch (Exception ex) {
            ex.printStackTrace();
            if (ex instanceof CartException) {
                throw new RuntimeException(((CartException) ex).getMessage());
            } else {
                throw ex;
            }
        }

        return saved;
    }

    public Order buildOrder(Cart cart, CheckOutRequest request) {
        try {
            OrderBuilder builder = Order.builder();

            User user = cart.getUser();
            PaymentMethod paymentMethod = paymentRepo.findById(request.getPayment_method_id()).get();
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
                    .withUser(user).build();
        } catch (Exception ex) {
            log.info("buildOrder error");
            ex.printStackTrace();
        }
        return null;

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
            throw e;
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
}
