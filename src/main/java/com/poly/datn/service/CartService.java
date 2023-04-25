package com.poly.datn.service;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;

import com.poly.datn.dto.request.CartDetailRequest;
import com.poly.datn.dto.request.CartItemRequest;
import com.poly.datn.dto.response.CartDetailResponse;
import com.poly.datn.dto.response.CartResponse;
import com.poly.datn.entity.Cart;
import com.poly.datn.entity.User;

public interface CartService {

    Cart findCartEntityByUserId(Integer userId);

    CartResponse findByUserId(Integer userId);

    CartResponse findByCartId(Integer cartId);

    List<CartDetailResponse> findAllItemsInCart(Integer userId);

    Integer exitsProductVariantInCart(Integer cartId, Integer variantId);

    boolean exitsById(Integer cartId);

    boolean existsByUserId(Integer userId, boolean checkEmpty);

    boolean isCartEmpty(Integer cartId);

    @Modifying(flushAutomatically = true)
    Integer updatedPriceSum(Integer cartId);

    Integer getRandomId();
    /* 
    ! Logged in user, authenticated-user scenario when userId is omitted
    * Overide later
    */
    CartResponse findCartOfCurrentUser();

    CartResponse mergeItemsToCart(List<CartItemRequest> items);

    boolean deleteAllItemsInCart(Integer cartId);

    boolean deleteAllWithIdIn(Integer cartId, List<Integer> ids);

    User getCurrentUser();

    CartResponse addProductToCart(List<CartDetailRequest> itmes);

    boolean updateCart();

    boolean updateCartByVariantStatus();
    boolean updateCartByInventory();
    
    CartResponse updateGuestCart(Integer cartId, List<CartDetailRequest> items);

    void refresh(Cart cart);
}
