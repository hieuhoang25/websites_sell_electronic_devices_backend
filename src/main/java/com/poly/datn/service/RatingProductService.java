package com.poly.datn.service;

import com.poly.datn.dto.request.RatingProductRequest;
import com.poly.datn.dto.response.ProductRatingResponse;

import java.util.List;

public interface RatingProductService {
    List<ProductRatingResponse> rateProduct(List<RatingProductRequest> request);
    Boolean isRating(Integer productId, Integer orderDetailId);
}
