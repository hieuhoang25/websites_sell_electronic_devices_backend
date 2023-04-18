package com.poly.datn.controller;

import static com.poly.datn.controller.router.Router.API.BASE;
import static com.poly.datn.controller.router.Router.FLASH_DEAL.CURRENT;
import static com.poly.datn.controller.router.Router.FLASH_DEAL.FLASH_DEAL;
import static com.poly.datn.controller.router.Router.FLASH_DEAL.TODAY;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.poly.datn.dto.request.FlashDealRequest;
import com.poly.datn.dto.response.FlashDealResponse;
import com.poly.datn.repository.PromotionProductRepository;
import com.poly.datn.service.FlashDealService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequiredArgsConstructor
@RequestMapping(BASE + FLASH_DEAL)
public class FlashDealController {

    final PromotionProductRepository repository;
    final FlashDealService dealService;

    @GetMapping(TODAY)
    public ResponseEntity<?> getTodayPromoList() {
        try {
            List<FlashDealResponse> deals = dealService.getTodayFlashDealExcludedExpire();
            if (deals == null) throw new RuntimeException();          
            return ResponseEntity.ok().body(deals);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Something went wrong, check log");
        }
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
        try {
            List<FlashDealResponse> list = dealService.getFlashDealFrom(request.getStart(), request.getEnd());
            if (list == null) throw new RuntimeException("Something went wrong!!!");
            return ResponseEntity.ok(list);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }

    }

    @GetMapping
    public ResponseEntity<List<FlashDealResponse>> getFlashDeal(){
        return ResponseEntity.ok(dealService.getFlashDeal());
    }

    @PutMapping("/removing-expired-promotion-of-product")
    public ResponseEntity removeExpiredPromotionOfProducts(){
        dealService.removeExpiredPromotionOfProduct();
        return ResponseEntity.noContent().build();
    }

}
