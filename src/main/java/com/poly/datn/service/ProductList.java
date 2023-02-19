package com.poly.datn.service;

import com.poly.datn.common.SearchResult;
import com.poly.datn.dto.response.ProductListResponse;
import org.springframework.data.domain.Pageable;

public interface ProductList {
    SearchResult<ProductListResponse> getAllProducts(Pageable pageable);
}
