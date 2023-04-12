package com.poly.datn.service;

import com.poly.datn.dto.request.RatingProductRequest;
import com.poly.datn.dto.response.OrderDetailResponse;
import com.poly.datn.dto.response.ProductRatingResponse;

import java.util.List;

public interface RatingProductService {
    void rateProduct(List<RatingProductRequest> request);
    List<OrderDetailResponse> isRating(Integer orderId);
}
