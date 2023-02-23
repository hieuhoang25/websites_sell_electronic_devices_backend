package com.poly.datn.service;

import com.poly.datn.dto.response.ProductColorResponse;

import java.util.List;

public interface ProductColor {
    List<ProductColorResponse> getProductColor(Integer productId);
}
