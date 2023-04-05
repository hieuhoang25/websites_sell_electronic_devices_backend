package com.poly.datn.service;

import com.poly.datn.dto.request.ProductRequest;
import com.poly.datn.dto.request.ProductVariantRequest;
import com.poly.datn.dto.response.Pagination;
import com.poly.datn.dto.response.ProductResponse;
import com.poly.datn.dto.response.ProductVariantResponse;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProductVariantService {

    Pagination<ProductVariantResponse> findByProductId(Pageable pageable, Integer id);

    ProductVariantResponse update(ProductVariantRequest productVariantRequest);
    ProductVariantResponse create(ProductVariantRequest productVariantRequest);

    void delete(Integer id,Integer isDeleted);

    ProductVariantResponse findById(Integer id);
}
