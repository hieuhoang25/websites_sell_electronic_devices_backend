package com.poly.datn.service.serviceImpl;

import java.util.List;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.poly.datn.common.mapper.ModelConverter;
import com.poly.datn.dto.request.CartDetailRequest;
import com.poly.datn.dto.request.CartItemRequest;
import com.poly.datn.dto.response.CartDetailResponse;
import com.poly.datn.dto.response.CartDetailResponse.CartDetailResponseBuilder;
import com.poly.datn.dto.response.ProductVariantResponse;
import com.poly.datn.entity.CartDetail;
import com.poly.datn.exception.cart.VariantAlreadyInCartException;
import com.poly.datn.exception.cart.VariantUnavailable;
import com.poly.datn.repository.CartDetailRepository;
import com.poly.datn.repository.ProductVariantRepository;
import com.poly.datn.service.CartDetailService;
import com.poly.datn.service.CartService;
import com.poly.datn.service.ProductVariantService;
import static com.poly.datn.dto.response.CartDetailResponse.withCartPrice_Detail;
import static com.poly.datn.dto.response.CartDetailResponse.withVariantDiscount_amount;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(rollbackFor = Exception.class)
public class CartDetailServiceImpl implements CartDetailService {

    final CartDetailRepository catDetailRepo;
    final ModelConverter modelConverter;
   
    final ProductVariantRepository variantRepo;
  
    @Autowired @Lazy
    private CartService cartService;

    @Autowired @Lazy
    private ProductVariantService variantService;

    @Override
    public List<CartDetailResponse> findAllByCartId(Integer cartId) {
        return cartService.findAllItemsInCart(cartId);

    }

    @Override
    public CartDetailResponse findByCartId(Integer cartId, Integer cartDetailId) {
        CartDetail detail = catDetailRepo.findByCartId(cartId, cartDetailId)
                .orElseThrow(() -> new EntityNotFoundException("cartdetail not found"));

        return modelConverter.map(detail, CartDetailResponse.class);
    }

    // after authenticated omitted cartId
    @Override
    public CartDetailResponse findCartDetailById(@Valid Integer cartDetailId) {
        return modelConverter.map(catDetailRepo.findById(cartDetailId).orElseThrow(
                () -> new EntityNotFoundException("CartDetail not found in cart")), CartDetailResponse.class);

    }

    @Override
    public CartDetailResponse add(CartItemRequest request) {
        try {
            Integer cartId = request.getCart_id();
            CartDetail entity = convertCartItemRequestToCartDetailForInsert(validateCartItemRequest(request));
            
            CartDetail newEntity =  catDetailRepo.saveAndFlush(entity);
            catDetailRepo.refresh(newEntity);
    
            log.info("saved C:" + request.getCart_id() + "V: " + request.getProduct_variant_id() + " Q: " +request.getQuantity() + "pridce_d: " + newEntity.getPrice_Detail());

            // ? how to call after update child
            cartService.updatedPriceSum(cartId);        
            
            return modelConverter.map(newEntity,
            CartDetailResponse.class) ;
        } catch (Exception ex) {
            if (ex instanceof VariantAlreadyInCartException) {
                log.info("catch VariantAlreadyInCartException in add() for variant: ", request.getProduct_variant_id());
                CartDetail cd =  catDetailRepo.findByProductVariantId(request.getCart_id(), request.getProduct_variant_id());
                Integer detailId = cd.getId();
                request.setQuantity(resolveQuantity(request.getProduct_variant_id(),cd.getQuantity() + request.getQuantity()));
                CartDetailRequest updateRequest =  request.toCartDetailRequest(detailId);
                 return update(updateRequest);
            }else if (ex instanceof VariantUnavailable)  {
                log.warn("delete variant " + request.getProduct_variant_id() +" from cart");
                return CartDetailResponse.getDeletedDetailResponse();
                // throw new RuntimeException(((VariantUnavailable)ex).getMessage()); 
            }else if (ex instanceof EntityNotFoundException) {
                log.warn(((EntityNotFoundException) ex).getMessage());
                return null;
            }
                
        }

        return null;

    }

   
    @Override
    public CartDetailResponse update(Integer cartId, CartDetailRequest request) {
        // map cartId when logged in
        request.setCart_id(cartId);
        return update(request);
    }

    @Override
    public CartDetailResponse update(CartDetailRequest request) {
        CartDetail detail = modelConverter.map(request, CartDetail.class);
        validateCartIdAndItemId(detail);
        CartDetail savedEntity = null;
        request.setQuantity(resolveQuantity(request.getProduct_variant_id(), request.getQuantity()));
        try {
            validateVariantStatus(request.getProduct_variant_id());
            
            // ! fix createdDate null when map from request
            detail.setCreateDate(catDetailRepo.findById(request.getId()).get().getCreateDate());
            
            savedEntity = catDetailRepo.saveAndFlush(detail);
            catDetailRepo.refresh(savedEntity);
        }catch(Exception ex) {
            
            if(ex instanceof VariantUnavailable) {
                log.info("remove item" + ((VariantUnavailable)ex).getMessage());
                
            //    ! removed all unvailable item
                 delete(request);
                return CartDetailResponse.getDeletedDetailResponse();
            }
        }finally {
            log.info("updating cart's sum_price ...");
            if(cartService.updatedPriceSum(request.getCart_id()) == 0) {
                log.info("updated sum_price failed");
                throw new RuntimeException("Cant' update cart sum");
            }
        }
        log.info("finished updating cart!!!");
        // catDetailRepo.refresh(detail);
        return modelConverter.map(savedEntity, CartDetailResponse.class);
    }

    @Override
    public void delete(CartDetailRequest request) {
        CartDetail entity = modelConverter.map(request, CartDetail.class);
        // log.info("call delete on " + request.getId() + " V: " + request.getProduct_variant_id());
        validateCartIdAndItemId(entity);
        catDetailRepo.deleteById(request.getId());
        cartService.updatedPriceSum(request.getCart_id());   
        log.info("finish removed");
    }

    public void validateCartIdAndItemId(CartDetail convertedDetail) {
        Integer cartId = convertedDetail.getCart().getId();
        Integer itemId = convertedDetail.getId();

        if(cartService.exitsById(cartId)) {
            if(!catDetailRepo.existsById(itemId)) {
                throw new EntityNotFoundException(
                    "Error: Item doesn't belong to cart (missmatch cart-cartdetail id)");
            }    
        }else {
            throw new EntityNotFoundException("Error: Cart not found");
        }
    }

    private CartItemRequest validateCartItemRequest(CartItemRequest request) {
        log.info("call exitsProductVariantInCart");
        Integer count = cartService.exitsProductVariantInCart(request.getCart_id(), request.getProduct_variant_id());

        log.warn(
            String.format("Cart: %d V: %d quan: %d", request.getCart_id(), request.getProduct_variant_id(), count));
        if (count >= 0) {
            throw new VariantAlreadyInCartException("Product's already in cart");
        }
        
        // vairant's status valid -> mapper doesn't deep map entity -> variant null
        validateVariantStatus(request.getProduct_variant_id());
        log.info("check variant available");
        request.setQuantity(resolveQuantity(request.getProduct_variant_id(), request.getQuantity()));

        log.info("validateCartItemRequest finished");
        return request;

    }

    private CartDetail convertCartItemRequestToCartDetailForInsert(CartItemRequest request) {
        return modelConverter.getTypeMap(CartItemRequest.class, CartDetail.class).setPostConverter(ctx -> {
            ctx.getDestination().setId(0);
            return ctx.getDestination();
        }).map(request);

    }

    private Integer resolveQuantity(Integer variantId,Integer quantity) {
        return quantity <= 0? 1: quantity > 5? 5 : quantity;
    }

    private boolean getStatusOfVariant(Integer variantId) {
        if(!variantRepo.existsById(variantId)) throw new EntityNotFoundException("Variant " + variantId + "not exist");
        return  variantRepo.isStatusTrue(variantId);
    }

    private void validateVariantStatus(Integer vairantId) {
        boolean status = getStatusOfVariant(vairantId);
        // log.info("validateVariantStatus status: " + status);
        if(!status)  throw new VariantUnavailable("Product's Variant Id is "+ vairantId +" unvailable");
    }

    @Override
    public CartDetailResponse updateGuestCartDetail(CartDetailRequest request) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updateGuestCartDetail'");
    }

    @Override
    public CartDetailResponse buildFromRequest(CartDetailRequest request) {
        Integer cartDetailId = cartService.getRandomId();
        CartDetailResponseBuilder respone = CartDetailResponse.getPlainCartDetailResponeBuilder(cartDetailId);
        try {
            ProductVariantResponse variant = variantService.findById(request.getProduct_variant_id());
            respone.withProductVariant(variant).withQuantity(request.getQuantity());
            withVariantDiscount_amount(respone);
            withCartPrice_Detail(respone);

            CartDetailResponse obj = respone.build();
            
            log.info("respone: price_detail " + obj.getPrice_detail() + " respone quantity: " + 
            obj.getQuantity());
            return obj;
            // return ResponseEntity.ok().body(obj);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("build from CartItemRequest request error: " + e.getMessage());
            return null;
        }
    }

}
