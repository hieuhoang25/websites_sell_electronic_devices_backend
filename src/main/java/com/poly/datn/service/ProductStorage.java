package com.poly.datn.service;

import com.poly.datn.dto.response.ProductStorageResponse;

import java.util.List;

public interface ProductStorage {
    List<ProductStorageResponse> getProductStorage(Integer productId, Integer colorId);
}
