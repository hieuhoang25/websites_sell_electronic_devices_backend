package com.poly.datn.service.serviceImpl;

import java.util.List;
import java.util.Set;

import javax.persistence.EntityNotFoundException;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.poly.datn.common.mapper.ModelConverter;
import com.poly.datn.dto.request.CartDetailRequest;
import com.poly.datn.dto.response.CartDetailResponse;
import com.poly.datn.dto.response.CartResponse;
import com.poly.datn.entity.Cart;
import com.poly.datn.entity.CartDetail;
import com.poly.datn.exception.cart.CartException;
import com.poly.datn.repository.CartDetailRepository;
import com.poly.datn.repository.CartRepository;
import com.poly.datn.service.CartService;
import com.poly.datn.dto.request.CartItemRequest;
import com.poly.datn.entity.Account;
import com.poly.datn.entity.User;
import com.poly.datn.repository.AccountRepository;
import com.poly.datn.service.CartDetailService;
import org.springframework.security.core.context.SecurityContextHolder;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@Transactional(rollbackFor = Exception.class)
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {

    private final CartRepository cartRepo;
    private final ModelConverter modelConverter;
    private final CartDetailRepository cartDetail;
    private final AccountRepository accountRepository;
    private final CartDetailService cartDetailService;

    @Override
    public CartResponse findByUserId(Integer userId) {
        return modelConverter.map(getCartEntityByUserId(userId), CartResponse.class);
    }

    @Override
    public CartResponse findByCartId(Integer cartId) {
      return modelConverter.map(getCartEntityByCartId(cartId), CartResponse.class);
    }

    @Override
    public Integer exitsProductVariantInCart(Integer cartId, Integer variantId) {
        if(!cartRepo.existsById(cartId)) throw new EntityNotFoundException("Cart not found");    
        return  cartDetail.countVariantQuantityInCart(cartId, variantId).orElse(-1);
    }

    // After authenticated
    @Override
    public CartResponse mergeItemsToCart(List<CartItemRequest> items) {
        Cart cart = getCartEntityByUserId(getCurrentUser().getId());
        try {
            items.forEach(i -> {
                cartDetailService.add(i);
            });
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        // log.info("cart size: " +  get);
        return  modelConverter.map(getCartEntityByUserId(getCurrentUser().getId()), CartResponse.class);

    }
   
    @Override
    public List<CartDetailResponse> findAllItemsInCart(Integer cartId){
        Set<CartDetail> items = getCartEntityByCartId(cartId).getCartDetails();
        return modelConverter.mapAllByIterator(items, CartDetailResponse.class);
    }

    private Cart getCartEntityByUserId(Integer userId) {
        return cartRepo.findCartByUserId(userId).orElseThrow(() -> new EntityNotFoundException("Cart not found"));
    }

    private Cart getCartEntityByCartId(Integer cartId) {
        return cartRepo.findById(cartId).orElseThrow(() -> new EntityNotFoundException("Cart not found"));
    }

    @Override
    public boolean exitsById(Integer cartId) {
        return cartRepo.existsById(cartId);
    }

    @Override
    public boolean isCartEmpty(Integer cartId) {
        return getCartEntityByCartId(cartId).getCartDetails().isEmpty();
    }

    @Override
    public Integer updatedPriceSum(Integer cartId) {
       return cartRepo.updateCartPriceSum(cartId);
    }

    @Override
    public boolean existsByUserId(Integer userId, boolean checkEmpty) {
       Cart cart = getCartEntityByUserId(userId);
       if(!checkEmpty) return true;

       if(cart.getCartDetails() == null || cart.getCartDetails().size() == 0) {
        throw new CartException("Cart is empty");
       }
       return true;
    }

    @Override
    public Cart findCartEntityByUserId(Integer userId) {
        return getCartEntityByUserId(userId);
    }

    @Override
    public boolean deleteAllItemsInCart(Integer cartId) {
        try {
            boolean isEmpty = isCartEmpty(cartId);
            Integer deleted  =  cartDetail.deleteAllByCartId(cartId);
            return (isEmpty && deleted == 0)? false : true;
        }catch(Exception ex) {
            ex.printStackTrace();
            throw new RuntimeException("Error: Can't delete items in cart");
        }
    }

    @Override
    public CartResponse findCartOfCurrentUser() {
            return findByUserId(getCurrentUser().getId());
    
    }

    public User getCurrentUser() {
        String name = SecurityContextHolder.getContext().getAuthentication().getName();
        Account account = accountRepository.findByUsername(name);
        return  account.getUser();
    }

    @Override
    public CartResponse updateCart(List<CartDetailRequest> items) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updateCart'");
    }

    @Override
    public CartResponse addProductToCart(List<CartDetailRequest> items) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updateCart'");
    }
}

    
