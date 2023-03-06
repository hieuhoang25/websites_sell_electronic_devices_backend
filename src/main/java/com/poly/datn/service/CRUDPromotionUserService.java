package com.poly.datn.service;


import com.poly.datn.dto.request.PromotionTypeRequest;
import com.poly.datn.dto.request.PromotionUserRequest;
import com.poly.datn.dto.response.PromotionTypeResponse;
import com.poly.datn.dto.response.PromotionUserResponse;

import java.util.List;

public interface CRUDPromotionUserService {

    PromotionUserResponse create(PromotionUserRequest promotionUserRequest);

    PromotionUserResponse update(PromotionUserRequest promotionUserRequest);

    void delete(Integer id);

    List<PromotionUserResponse> findAll();

    PromotionUserResponse findById(Integer id);

}
