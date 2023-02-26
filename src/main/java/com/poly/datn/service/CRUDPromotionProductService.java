package com.poly.datn.service;


import com.poly.datn.dto.request.PromotionProductRequest;
import com.poly.datn.dto.request.PromotionTypeRequest;
import com.poly.datn.dto.response.PromotionProductResponse;
import com.poly.datn.dto.response.PromotionTypeResponse;

import java.util.List;

public interface CRUDPromotionProductService {

    PromotionProductResponse create(PromotionProductRequest promotionProductRequest);

    PromotionProductResponse update(PromotionProductRequest promotionProductRequest);

    void delete(Integer id);

    List<PromotionProductResponse> findAll();

    PromotionProductResponse findById(Integer id);

}
