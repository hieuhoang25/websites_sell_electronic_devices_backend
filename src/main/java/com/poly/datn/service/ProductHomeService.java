package com.poly.datn.service;

import com.poly.datn.dto.response.ProductFilterResponse;

import java.util.List;

public interface ProductHomeService {
    List<ProductFilterResponse> bigDiscount();
    List<ProductFilterResponse> newArrival();

    List<ProductFilterResponse> topSales();
}
