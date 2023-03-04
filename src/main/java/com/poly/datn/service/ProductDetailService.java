package com.poly.datn.service;

import com.poly.datn.dto.request.ProductDetailRequest;
import com.poly.datn.dto.response.ProductDetailResponse;

public interface ProductDetailService {
    ProductDetailResponse getProductDetail(ProductDetailRequest productDetailRequest);
}
