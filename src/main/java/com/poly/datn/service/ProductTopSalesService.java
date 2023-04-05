package com.poly.datn.service;

import com.poly.datn.dto.response.ProductFilterResponse;

import java.util.List;

public interface ProductTopSalesService {
    List<ProductFilterResponse> topSales();
    List<ProductFilterResponse> newArrival();
}
