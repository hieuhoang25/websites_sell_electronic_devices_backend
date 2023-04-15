package com.poly.datn.service;

import java.util.List;

import com.poly.datn.dto.request.FindAllUserPromoRequest;
import com.poly.datn.dto.response.FindAllUserPromoResponse;
import com.poly.datn.entity.PromotionUser;

public interface FindAllUserPromotionService {
    

    List<FindAllUserPromoResponse> findAllUserPromo(Integer userId);
    List<FindAllUserPromoResponse> findAllValidUserPromo(Integer userId);

    List<FindAllUserPromoResponse> findPromoByCode(Integer userId, String code);
    
    List<FindAllUserPromoResponse> findPromoByCode(Integer userId, FindAllUserPromoRequest request);
    List<FindAllUserPromoResponse> findPromoForOrder(Integer userId, FindAllUserPromoRequest request);


}
