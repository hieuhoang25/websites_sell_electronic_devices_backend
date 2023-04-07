package com.poly.datn.controller;

import com.poly.datn.controller.router.Router;
import com.poly.datn.dto.response.Pagination;
import com.poly.datn.service.ProductHomeService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

import static com.poly.datn.controller.router.Router.API.*;

@RequiredArgsConstructor
@RequestMapping(Router.API.BASE)
@RestController

public class ProductHomeController {
    private final ProductHomeService service;

    @GetMapping(PRODUCT_BIG_DISCOUNT)
    @Tag(name = Router.API.BASE + PRODUCT_BIG_DISCOUNT)
    public ResponseEntity<Pagination> bigDiscount(
            @RequestParam Optional<Integer> size,
            @RequestParam Optional<Integer> page
    ) {
        Pageable pageable = PageRequest.of(page.orElse(0), size.orElse(10));
        return ResponseEntity.ok(service.bigDiscount(pageable));
    }

    @GetMapping(PRODUCT_ARRIVAL)
    @Tag(name = Router.API.BASE + PRODUCT_ARRIVAL)
    public ResponseEntity<Pagination> productArrival(
            @RequestParam Optional<Integer> size,
            @RequestParam Optional<Integer> page
    ) {
        Pageable pageable = PageRequest.of(page.orElse(0), size.orElse(10));
        return ResponseEntity.ok(service.newArrival(pageable));
    }

    @GetMapping(PRODUCT_TOP_SALES)
    @Tag(name = Router.API.BASE + PRODUCT_TOP_SALES)
    public ResponseEntity<Pagination> topSales(
            @RequestParam Optional<Integer> size,
            @RequestParam Optional<Integer> page
    ) {
        Pageable pageable = PageRequest.of(page.orElse(0), size.orElse(10));
        return ResponseEntity.ok(service.topSales(pageable));
    }
}
