package com.poly.datn.service;

import com.poly.datn.dto.request.ProductAttributeRequest;
import com.poly.datn.dto.request.ProductVariantRequest;
import com.poly.datn.dto.response.ProductAttributeResponse;
import com.poly.datn.dto.response.ProductVariantResponse;

import java.util.List;

public interface ProductAttributeService {
    ProductAttributeResponse create(ProductAttributeRequest productAttributeRequest);

    List<ProductAttributeResponse> update(List<ProductAttributeRequest> productAttributeRequest);

    List<ProductAttributeResponse> findByProductId(Integer id);

    List<ProductAttributeResponse> findAll();

    void delete(List<Integer> listId);



}
