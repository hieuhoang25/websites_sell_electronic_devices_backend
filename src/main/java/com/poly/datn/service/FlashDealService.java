package com.poly.datn.service;

import java.time.LocalDateTime;
import java.util.List;

import com.poly.datn.dto.response.FlashDealResponse;

public interface FlashDealService {

    public List<FlashDealResponse> getCurrentFlashDeal();
   
    public List<FlashDealResponse> getTodayFlashDeal();

    public List<FlashDealResponse> getTodayFlashDealExcludedExpire();

    List<FlashDealResponse> getFlashDeal();

    void removeExpiredPromotionOfProduct();

    public List<FlashDealResponse> getFlashDealFrom(LocalDateTime start, LocalDateTime end);

    public List<FlashDealResponse> getFlashDealFromExcludedExpire(LocalDateTime start, LocalDateTime end);


}
