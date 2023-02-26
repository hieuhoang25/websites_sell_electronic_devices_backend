package com.poly.datn.service;


import com.poly.datn.dto.request.PromotionTypeRequest;
import com.poly.datn.dto.response.PromotionTypeResponse;

import java.util.List;

public interface CRUDPromotionTypeService {

    PromotionTypeResponse create(PromotionTypeRequest promotionTypeRequest);

    PromotionTypeResponse update(PromotionTypeRequest promotionTypeRequest);

    void delete(Integer id);

    List<PromotionTypeResponse> findAll();

    PromotionTypeResponse findById(Integer id);

}
