package com.poly.datn.service;

import com.poly.datn.dto.request.ProductRequest;
import com.poly.datn.dto.request.ProductVariantRequest;
import com.poly.datn.dto.response.ProductResponse;
import com.poly.datn.dto.response.ProductVariantResponse;

import java.util.List;

public interface ProductVariantService {

    List<ProductVariantResponse> findByProductId(Integer id);

    ProductVariantResponse update(ProductVariantRequest productVariantRequest);
    ProductVariantResponse create(ProductVariantRequest productVariantRequest);
}
