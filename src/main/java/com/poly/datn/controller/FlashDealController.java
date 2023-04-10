package com.poly.datn.controller;

import static com.poly.datn.controller.router.Router.API.BASE;
import static com.poly.datn.controller.router.Router.FLASH_DEAL.CURRENT;
import static com.poly.datn.controller.router.Router.FLASH_DEAL.FLASH_DEAL;
import static com.poly.datn.controller.router.Router.FLASH_DEAL.TODAY;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.List;

import javax.management.RuntimeErrorException;
import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.poly.datn.dto.request.FlashDealRequest;
import com.poly.datn.dto.response.FlashDealResponse;
import com.poly.datn.entity.PromotionProduct;
import com.poly.datn.repository.PromotionProductRepository;
import com.poly.datn.service.FlashDealService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequiredArgsConstructor
@RequestMapping(BASE + FLASH_DEAL)
@Slf4j
public class FlashDealController {

    final PromotionProductRepository repository;
    final FlashDealService dealService;

    @GetMapping(TODAY)
    public ResponseEntity<?> getTodayPromoList() {
        try {
            LocalDateTime start = LocalDate.now().atStartOfDay();
            LocalDateTime end = LocalDate.now().atTime(LocalTime.MAX);
            ZoneOffset offset = OffsetDateTime.now().getOffset();

            List<PromotionProduct> list = repository.findByUpdatedDateBetweenAndActivate(start.toInstant(offset),
                    end.toInstant(offset), true);
            log.info("size: " + list.size());
            List<FlashDealResponse> deals = dealService.getTodayFlashDeal();

            if (deals == null)
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Something went wrong, check log");
            return ResponseEntity.ok().body(deals);
        } catch (Exception e) {
            e.printStackTrace();
        }

        // log.info("product: " list.size());
        return ResponseEntity.ok("ok");
    }

    @GetMapping(CURRENT)
    public ResponseEntity<?> getCurrentDeal() {
        try {
            return ResponseEntity.ok().body(dealService.getCurrentFlashDeal());
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("something went wrong");
        }
    }

    @PostMapping
    public ResponseEntity<?> getDealFrom(@Valid @RequestBody FlashDealRequest request) {
        log.info("start: " + request.getStart().toString());
        log.info("end: " + request.getEnd().toString());
        try {

            List<FlashDealResponse> list = dealService.getFlashDealFrom(request.getStart(), request.getEnd());
            if (list == null)
                throw new RuntimeException("Something went wrong!!!");
            return ResponseEntity.ok(list);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }

    }

}
