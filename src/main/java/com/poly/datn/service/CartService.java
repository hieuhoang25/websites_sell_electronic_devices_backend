package com.poly.datn.service;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;

import com.poly.datn.dto.request.CartDetailRequest;
import com.poly.datn.dto.response.CartDetailResponse;
import com.poly.datn.dto.response.CartResponse;
import com.poly.datn.entity.Cart;

public interface CartService {

    Cart findCartEntityByUserId(Integer userId);

    CartResponse findByUserId(Integer userId);

    CartResponse findByCartId(Integer cartId);

    List<CartDetailResponse> findAllItemsInCart(Integer userId);

    Integer exitsProductVariantInCart(Integer cartId, Integer variantId);

    boolean exitsById(Integer cartId);

    boolean existsByUserId(Integer userId, boolean checkEmpty);

    boolean isCartEmpty(Integer cartId);

    CartResponse addProductToCart(List<CartDetailRequest> itmes);

    CartResponse updateCart(List<CartDetailRequest> items);

    boolean deleteAllItemsInCart(Integer cartId);


    @Modifying(flushAutomatically = true)
    Integer updatedPriceSum(Integer cartId);
    /* 
    ! Logged in user, authenticated-user scenario when userId is omitted
    * Overide later
    */
    List<CartDetailResponse> findAllItemsInCart();
}
