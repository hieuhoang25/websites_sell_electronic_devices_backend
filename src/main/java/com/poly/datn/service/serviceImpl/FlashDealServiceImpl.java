package com.poly.datn.service.serviceImpl;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.poly.datn.dto.response.FlashDealResponse;
import com.poly.datn.dto.response.FlashDealResponse.FlashDealResponseBuilder;
import com.poly.datn.entity.PromotionProduct;
import com.poly.datn.repository.PromotionProductRepository;
import com.poly.datn.service.FlashDealService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;
import org.webjars.NotFoundException;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional(rollbackFor = {Exception.class})
public class FlashDealServiceImpl implements FlashDealService {

    private final PromotionProductRepository promotionProductRepository;
    private final ZoneId idZ = ZoneId.of("Asia/Ho_Chi_Minh");

    @Override
    public List<FlashDealResponse> getCurrentFlashDeal() {
        LocalDateTime start = getStartOfToday();
        LocalDateTime end = getEndOfToday();

        Instant from = convertToInstant(start), to = convertToInstant(end);

        List<PromotionProduct> promos = promotionProductRepository
                .findByUpdatedDateBetweenOrderByUpdatedDateAsc(from, to);
        log.info("remove expire promo");
        excludeExpiration(promos, getLocalDatetimeNow());
        return buildResponse(promos);
    }

    @Override
    public List<FlashDealResponse> getTodayFlashDeal() {
        try {
            LocalDateTime start = getStartOfToday();
            LocalDateTime end = getEndOfToday();
            List<PromotionProduct> promos = promotionProductRepository
                    .findByUpdatedDateBetweenOrderByUpdatedDateAsc(convertToInstant(start), convertToInstant(end));

            excludeExpiration(promos, getLocalDatetimeNow());
            return buildResponse(promos);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<FlashDealResponse> getFlashDeal() {
        LocalDateTime now = getLocalDatetimeNow();
        List<PromotionProduct> promos = promotionProductRepository
                .findAllValidPromotion(convertToInstant(now));
        if (promos.isEmpty())
            throw new NotFoundException("No promotion found!");
        return buildResponse(promos);
    }

    @Override
    public void removeExpiredPromotionOfProduct() {
        LocalDateTime now = getLocalDatetimeNow();
        promotionProductRepository.removeAllPromotionExpiredOfProduct(convertToInstant(now));
    }

    @Override
    public List<FlashDealResponse> getFlashDealFrom(LocalDateTime start, LocalDateTime end) {
        Instant from = convertToInstant(start);
        Instant to = convertToInstant(end);
        List<PromotionProduct> promos = promotionProductRepository
                .findByUpdatedDateBetweenAndActivateOrderByUpdatedDateAsc(from, to, true);
        return buildResponse(promos);
    }

    @Override
    public List<FlashDealResponse> getTodayFlashDealExcludedExpire() {
        try {
            LocalDateTime now = getLocalDatetimeNow();
            LocalDateTime start = getStartOfToday();
            LocalDateTime end = getEndOfToday();

            List<PromotionProduct> promos = promotionProductRepository
                    .findByUpdatedDateBetweenAndActivateOrderByUpdatedDateAsc(convertToInstant(start),
                            convertToInstant(end), true);
            // excludeExpiration(promos, now);
            promos.removeIf(p -> p.getExpirationDate().isBefore(convertToInstant(now)));
            return buildResponse(promos);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<FlashDealResponse> getFlashDealFromExcludedExpire(LocalDateTime start, LocalDateTime end) {
        Instant from = convertToInstant(start);
        Instant to = convertToInstant(end);

        List<PromotionProduct> promos = promotionProductRepository
                .findByUpdatedDateBetweenAndActivateOrderByUpdatedDateAsc(from, to, true);

        // remove expired one
        excludeExpiration(promos, start);

        return buildResponse(promos);
    }

    public List<PromotionProduct> excludeExpiration(List<PromotionProduct> promos, LocalDateTime current) {
        if (promos == null)
            return new ArrayList<>();
        promos.removeIf(p -> p.getExpirationDate().isBefore(convertToInstant(current)));
        return promos;
    }

    public List<FlashDealResponse> buildResponse(List<PromotionProduct> promos) {
        if (promos == null)
            return new ArrayList<FlashDealResponse>();
        try {
            FlashDealResponseBuilder builder = FlashDealResponse.builder();
            // .FlashDealResponseBuilder;
            List<FlashDealResponse> dealList = promos.stream().map(item -> builder
                    .withActivate(item.getActivate())
                    .withName(item.getName())
                    .withExpired_time(convertInstantToLocalDateTime(item.getExpirationDate()))
                    .withStart_time(convertInstantToLocalDateTime(item.getUpdatedDate()))
                    .withCurrent_time(getLocalDatetimeNow())
                    .withStart(item.getIsStart())
                    .build()
                    .setProductResponseList(item.getProducts())).collect(Collectors.toList());

            return dealList;
        } catch (Exception e) {
            log.error("build dealResponse failer");
            e.printStackTrace();
        }
        return null;

    }

    public Instant convertToInstant(LocalDateTime time) {
        ZoneOffset offset = OffsetDateTime.ofInstant(Instant.now(), idZ).getOffset();
        return time.toInstant(offset);
    }

    public LocalDateTime convertInstantToLocalDateTime(Instant instant) {
        ZoneOffset offset = OffsetDateTime.now().getOffset();
        return LocalDateTime.ofInstant(instant, offset);
    }

    public LocalDateTime getLocalDatetimeNow() {
        return LocalDateTime.now(idZ);
    }

    public LocalDateTime getStartOfToday() {
        return LocalDate.now(idZ).atStartOfDay();
    }

    public LocalDateTime getEndOfToday() {
        return LocalDate.now(idZ).atTime(LocalTime.MAX);
    }
}
