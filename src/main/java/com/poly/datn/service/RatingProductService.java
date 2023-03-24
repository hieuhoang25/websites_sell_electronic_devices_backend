package com.poly.datn.service;

import com.poly.datn.dto.request.RatingProductRequest;
import com.poly.datn.dto.response.ProductRatingResponse;

public interface RatingProductService {
    ProductRatingResponse rateProduct(RatingProductRequest request);
    Boolean isRating(Integer productId, Integer orderDetailId);
}
