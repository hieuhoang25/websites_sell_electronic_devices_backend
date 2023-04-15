package com.poly.datn.service;

import java.util.List;

import com.poly.datn.dto.request.CartDetailRequest;
import com.poly.datn.dto.request.CartItemRequest;
import com.poly.datn.dto.response.CartDetailResponse;
import com.poly.datn.dto.response.CartResponse;

public interface CartDetailService {
    
    CartDetailResponse findCartDetailById(Integer cartDetailId);

    CartDetailResponse findByCartId(Integer cartId, Integer cartDetailId);

    List<CartDetailResponse> findAllByCartId(Integer cartId);

    CartDetailResponse update(CartDetailRequest request);

    CartDetailResponse updateGuestCartDetail(CartDetailRequest request);

    CartDetailResponse update(Integer cartId,CartDetailRequest request);

    CartDetailResponse add(CartItemRequest request);

    CartDetailResponse buildFromRequest(CartDetailRequest request);

    void delete(CartDetailRequest request);
}
