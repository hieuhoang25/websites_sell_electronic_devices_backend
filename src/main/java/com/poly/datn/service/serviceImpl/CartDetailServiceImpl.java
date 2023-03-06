package com.poly.datn.service.serviceImpl;

import java.util.List;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.poly.datn.common.mapper.ModelConverter;
import com.poly.datn.dto.request.CartDetailRequest;
import com.poly.datn.dto.request.CartItemRequest;
import com.poly.datn.dto.response.CartDetailResponse;
import com.poly.datn.entity.CartDetail;
import com.poly.datn.exception.cart.VariantAlreadyInCartException;
import com.poly.datn.exception.cart.VariantUnavailable;
import com.poly.datn.repository.CartDetailRepository;
import com.poly.datn.repository.ProductVariantRepository;
import com.poly.datn.service.CartDetailService;
import com.poly.datn.service.CartService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(rollbackFor = Exception.class)
public class CartDetailServiceImpl implements CartDetailService {

    final CartDetailRepository catDetailRepo;
    final ModelConverter modelConverter;
    final CartService cartService;
    final ProductVariantRepository variantRepo;

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
            
            // ? debug clean later
            // log.info(" entity " + entity.getId() + " " + entity.getCart().getId() + " " + entity.getQuantity() + " "
            //         + entity.getProductVariant().getId() + " " + entity.getProductVariant().getStatus());
            
            CartDetail newEntity =  catDetailRepo.save(entity);
            // ? how to call after update child
            cartService.updatedPriceSum(cartId);        
            
            return modelConverter.map(newEntity,
            CartDetailResponse.class) ;
        } catch (Exception ex) {
            if (ex instanceof VariantAlreadyInCartException)
                throw (VariantAlreadyInCartException) ex;
            else if (ex instanceof VariantUnavailable)  {
                throw new RuntimeException(((VariantUnavailable)ex).getMessage()); 
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
        request.setQuantity(resolveQuantity(request.getQuantity()));
        try {

            validateVariantStatus(request.getProduct_variant_id());
            catDetailRepo.save(detail);
        }catch(Exception ex) {
            
            if(ex instanceof VariantUnavailable) {
                log.info("remove item" + ((VariantUnavailable)ex).getMessage());
            //    ! removed all unvailable item
                // delete(request);
            }
        }finally {
            log.info("updating cart's sum_price ...");
            if(cartService.updatedPriceSum(request.getCart_id()) == 0) {
                log.info("updated sum_price failed");
                throw new RuntimeException("Cant' update cart sum");
            }
        }
        log.info("finished updating cart!!!");
        return modelConverter.map(detail, CartDetailResponse.class);
    }

    @Override
    public void delete(CartDetailRequest request) {
        CartDetail entity = modelConverter.map(request, CartDetail.class);

        validateCartIdAndItemId(entity);
        catDetailRepo.deleteById(entity.getId());

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
        if (count > 0) {
            throw new VariantAlreadyInCartException("Product's already in cart");
        }
        
        // vairant's status valid -> mapper doesn't deep map entity -> variant null
        validateVariantStatus(request.getProduct_variant_id());
        
        request.setQuantity(resolveQuantity(request.getQuantity()));

        return request;

    }

    private CartDetail convertCartItemRequestToCartDetailForInsert(CartItemRequest request) {
        return modelConverter.getTypeMap(CartItemRequest.class, CartDetail.class).setPostConverter(ctx -> {
            ctx.getDestination().setId(0);
            return ctx.getDestination();
        }).map(request);

    }

    private Integer resolveQuantity(Integer quantity) {
        return quantity <= 0? 1: quantity > 5? 5 : quantity;
    }

    private boolean getStatusOfVariant(Integer variantId) {
        return  variantRepo.isStatusTrue(variantId);
    }

    private void validateVariantStatus(Integer vairantId) {
        if(!getStatusOfVariant(vairantId))  throw new VariantUnavailable("Product's Variant Id is "+ vairantId +" unvailable");
    }

}
