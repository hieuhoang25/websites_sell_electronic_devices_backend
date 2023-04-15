package com.poly.datn.service;

import com.poly.datn.dto.request.InventoryRequest;
import com.poly.datn.dto.request.ProductDetailRequest;
import com.poly.datn.dto.response.InventoryResponse;
import com.poly.datn.dto.response.ProductDetailResponse;

public interface ProductDetailService {
    ProductDetailResponse getProductDetail(ProductDetailRequest productDetailRequest);

    InventoryResponse getInventory(InventoryRequest request);
}
