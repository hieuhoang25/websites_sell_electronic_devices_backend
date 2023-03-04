package com.poly.datn.service;

import java.util.List;

import com.poly.datn.dto.request.CartDetailRequest;
import com.poly.datn.dto.request.CartItemRequest;
import com.poly.datn.dto.response.CartResponse;

public interface CartService {

    
    CartResponse findByUserId(Integer userId);

    CartResponse addProductToCart(List<CartDetailRequest> itmes);

    CartResponse updateCart(List<CartDetailRequest> items);

    boolean exitsProductVariantInCart(Integer variantId);
}
