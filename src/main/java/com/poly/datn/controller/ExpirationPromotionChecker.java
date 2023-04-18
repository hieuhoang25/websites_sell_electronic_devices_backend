package com.poly.datn.controller;

import com.poly.datn.repository.PromotionProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.time.LocalDateTime;
import java.time.ZoneId;

@RequiredArgsConstructor
@Configuration
@EnableScheduling
public class ExpirationPromotionChecker {
    private final PromotionProductRepository repository;
    @Scheduled(cron = "0 0 0 * * *",zone = "Asia/Ho_Chi_Minh") // check every day at midnight (adjust as needed)
    public void checkPromotionExpirationDate() {
        removeAllExpiredPromotion();
    }

    public void removeAllExpiredPromotion(){
        ZoneId zoneId = ZoneId.of("Asia/Ho_Chi_Minh");
        LocalDateTime today = LocalDateTime.now();
        repository.removeAllPromotionExpiredOfProduct(today.atZone(zoneId).toInstant());
    }
}
