package com.poly.datn.service.serviceImpl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.poly.datn.common.mapper.ModelConverter;
import com.poly.datn.dto.request.CartDetailRequest;
import com.poly.datn.dto.request.CartItemRequest;
import com.poly.datn.dto.response.CartResponse;
import com.poly.datn.dto.response.CartDetailResponse;
import com.poly.datn.dto.response.CartResponse.CartResponseBuilder;
import com.poly.datn.entity.Account;
import com.poly.datn.entity.Cart;
import com.poly.datn.entity.CartDetail;
import com.poly.datn.entity.User;
import com.poly.datn.exception.cart.CartException;
import com.poly.datn.repository.AccountRepository;
import com.poly.datn.repository.CartDetailRepository;
import com.poly.datn.repository.CartRepository;
import com.poly.datn.repository.UserRepository;
import com.poly.datn.service.CartDetailService;
import com.poly.datn.service.CartService;
import com.poly.datn.service.UserInfoByTokenService;


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
    private final UserInfoByTokenService userInfoService;;
    private final UserRepository userRepository;
 

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
        return findByUserId(getCurrentUser().getId());

    }
   
    @Override
    public List<CartDetailResponse> findAllItemsInCart(Integer cartId){
        // !replace
        // Set<CartDetail> items = getCartEntityByCartId(cartId).getCartDetails();
        // return modelConverter.mapAllByIterator(items, CartDetailResponse.class);
        log.info("ordered ....");
        List<CartDetail> items = cartDetail.findAllByCartIdOrderByCreateDateDesc(cartId).orElseGet(ArrayList::new);
        return modelConverter.mapAllByIterator(items, CartDetailResponse.class);

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

    private Cart getCartEntityByUserId(Integer userId) {
        try {
            return cartRepo.findCartByUserId(userId).orElseThrow(() -> new EntityNotFoundException("Cart not found"));
        }catch(Exception e) {
            if(e instanceof EntityNotFoundException) {
                User currentUser = getCurrentUser();
                log.warn("Cart not found by user with id: ", userId);
                log.info("Create new Cart for user if not exists");
                currentUser.setCarts(new Cart(0));
                userRepository.saveAndFlush(currentUser);
                log.info("created new cart for user done");
               log.info("user cart: " + currentUser.getCarts());
               return currentUser.getCarts();
            }else {
                log.error("Other error when find cart by user id");
                throw new RuntimeException(e.getMessage());
            }
        }
       
    }

    private Cart getCartEntityByCartId(Integer cartId) {
        return cartRepo.findById(cartId).orElseThrow(() -> new EntityNotFoundException("Cart not found"));
    }

    @Override
    public CartResponse findByUserId(Integer userId) {
        // try {
            
            // return modelConverter.map(getCartEntityByUserId(userId), CartResponse.class);
        // }catch(Exception e) {
        //     if(e instanceof EntityNotFoundException) {
        //         User currentUser = userInfoService.getCurrentUser();
        //         log.warn("Cart not found by user with id: ", userId);
        //         log.info("Create new Cart for user if not exists");
        //         currentUser.setCarts(new Cart(0));
        //         userRepository.save(currentUser);
        //         log.info("created new cart for user done");
        //        log.info("user cart: ", currentUser.getCarts());
            //    return modelConverter.map(currentUser.getCarts(), CartResponse.class);
            // }else {
            //     log.error("Other error when find cart by user id");
            //     throw new RuntimeException(e.getMessage());
            // }
        // }
        CartResponse response = modelConverter.map(getCartEntityByUserId(userId), CartResponse.class);
        return  response.sortCartDetailsByCreateDateDesc();
    }

    @Override
    public CartResponse findCartOfCurrentUser() {
        try {
            User currentUser =  getCurrentUser();
            return findByUserId(currentUser.getId());
        }catch(Exception e) {
            e.printStackTrace();
            log.error("Can't find cart of current user", e.getMessage());
            return null;
        } 
    }


    @Override
    public boolean deleteAllItemsInCart(Integer cartId) {
        try {
            boolean isEmpty = isCartEmpty(cartId);
            Integer deleted  =  cartDetail.deleteAllByCartId(cartId);
            cartRepo.findById(cartId);
            return (isEmpty && deleted == 0)? false : true;
        }catch(Exception ex) {
            ex.printStackTrace();
            throw new RuntimeException("Error: Can't delete items in cart");
        }
    }
   
    public User getCurrentUser() {
        User user = userInfoService.getCurrentUser();
        log.info("get Current user : " + user.getEmail());
        return  user;
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

    @Override
    public CartResponse updateGuestCart(Integer cartId, List<CartDetailRequest> items) {

        CartResponseBuilder cartBuilder =  CartResponse.getAnnonCartResponseBuilder(cartId);

        if(items.isEmpty()) return cartBuilder.build();

        
        
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updateGuestCart'");
    }
}

    
