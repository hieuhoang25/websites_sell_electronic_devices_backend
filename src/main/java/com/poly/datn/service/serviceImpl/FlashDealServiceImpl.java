package com.poly.datn.service.serviceImpl;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.poly.datn.common.mapper.ModelConverter;
import com.poly.datn.dto.response.FlashDealResponse;
import com.poly.datn.dto.response.FlashDealResponse.FlashDealResponseBuilder;
import com.poly.datn.entity.PromotionProduct;
import com.poly.datn.repository.PromotionProductRepository;
import com.poly.datn.service.FlashDealService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class FlashDealServiceImpl implements FlashDealService {

    private final PromotionProductRepository promotionProductRepository;
    private final ModelConverter converter;

    @Override
    public List<FlashDealResponse> getCurrentFlashDeal() {
        ZoneOffset offset = OffsetDateTime.now().getOffset();
        LocalDateTime start = LocalDateTime.now();
        LocalDateTime end = LocalDate.now().atTime(LocalTime.MAX);

        Instant from = start.toInstant(offset), to = end.toInstant(offset);

        List<PromotionProduct> promos = promotionProductRepository
                .findByUpdatedDateBetweenOrderByUpdatedDateAsc(from, to);

        log.info("remove expire promo");
        promos.removeIf(p -> p.getExpirationDate().isBefore(from)); 

        return  buildResponse(promos);
        // return  buildResponse(promos);
    }

    @Override
    public List<FlashDealResponse> getTodayFlashDeal() {
        try {
       
            LocalDateTime start = LocalDate.now().atStartOfDay();
            LocalDateTime end = LocalDate.now().atTime(LocalTime.MAX);
            List<PromotionProduct> promos = promotionProductRepository
                    .findByUpdatedDateBetweenOrderByUpdatedDateAsc(convertToInstant(start) , convertToInstant(end));
        
             return buildResponse(promos);       
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<FlashDealResponse> buildResponse(List<PromotionProduct> promos) {
        try {
            FlashDealResponseBuilder builder = FlashDealResponse.builder();
            FlashDealResponse response = new FlashDealResponse();
            response.setProducts(null);
            // .FlashDealResponseBuilder;
            List<FlashDealResponse> dealList = promos.stream().map(item -> builder
                    .withActivate(item.getActivate())
                    .withName(item.getName())
                    .withExpired_time(convertInstantToLocalDateTime(item.getExpirationDate()))
                    .withStart_time(convertInstantToLocalDateTime(item.getUpdatedDate()))
                    .build()
                    .setProductResponseList(item.getProducts())).collect(Collectors.toList());

            return dealList;
        } catch (Exception e) {
            log.error("build dealResponse failer");
            e.printStackTrace();
        }
        return null;

    }

    @Override
    public List<FlashDealResponse> getFlashDealFrom(LocalDateTime  start, LocalDateTime end) {
       
        Instant from = convertToInstant(start);
        Instant to = convertToInstant(end);
        List<PromotionProduct> promos =  promotionProductRepository.findByUpdatedDateBetweenAndActivateOrderByUpdatedDateAsc(from,to,true);
      return  buildResponse(promos);
    }

    public Instant convertToInstant(LocalDateTime time) {
        ZoneOffset offset = OffsetDateTime.now().getOffset();
        return time.toInstant(offset);
    } 

    public LocalDateTime convertInstantToLocalDateTime(Instant instant) {
        ZoneOffset offset = OffsetDateTime.now().getOffset();
        return LocalDateTime.ofInstant(instant,offset);
    }

    @Override
    public List<FlashDealResponse> getTodayFlashDealExcludedExpire() {
        try {
            LocalDateTime start = LocalDate.now().atStartOfDay();
            LocalDateTime end = LocalDate.now().atTime(LocalTime.MAX);
            List<PromotionProduct> promos = promotionProductRepository
                    .findByUpdatedDateBetweenOrderByUpdatedDateAsc(convertToInstant(start) , convertToInstant(end));
            
                    promos.removeIf(p -> p.getExpirationDate().isBefore(convertToInstant(start))); 
                    return buildResponse(promos);
        }catch(Exception e) {
            e.printStackTrace();
        }
        return null;
       
  
    }


}
