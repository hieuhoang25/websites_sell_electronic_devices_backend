package com.poly.datn.service;

import com.poly.datn.dto.request.CartDetailRequest;
import com.poly.datn.dto.response.CartDetailResponse;

public interface CartDetailService {
    
    CartDetailResponse getCartDetailById(Integer cartDetailId);

    void update(CartDetailRequest request);

    void add(CartDetailRequest request);

    void delete(CartDetailRequest request);
}
