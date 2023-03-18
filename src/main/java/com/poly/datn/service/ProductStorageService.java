package com.poly.datn.service;

import com.poly.datn.dto.response.ProductStorageResponse;

import java.util.List;

public interface ProductStorageService {
    List<ProductStorageResponse> getProductStorage(Integer productId, Integer colorId);
    List<ProductStorageResponse> getStorage();
}
