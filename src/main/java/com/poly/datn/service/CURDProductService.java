package com.poly.datn.service;

import com.poly.datn.dto.request.ProductRequest;
import com.poly.datn.dto.response.ProductResponse;

import java.util.List;

public interface CURDProductService {
    List<ProductResponse> findAll();

    ProductResponse create(ProductRequest productRequest);

    ProductResponse update(ProductRequest productRequest);

    void delete(Integer id);


}
