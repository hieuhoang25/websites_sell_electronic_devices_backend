package com.poly.datn.controller;

import com.poly.datn.controller.router.Router;
import com.poly.datn.dto.response.ProductFilterResponse;
import com.poly.datn.service.ProductTopSalesService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.poly.datn.controller.router.Router.API.PRODUCT_ARRIVAL;
import static com.poly.datn.controller.router.Router.API.PRODUCT_TOP_SALES;

@RequiredArgsConstructor
@RequestMapping(Router.API.BASE)
@RestController

public class ProductTopSalesController {
    private final ProductTopSalesService service;

    @GetMapping(PRODUCT_TOP_SALES)
    @Tag(name = Router.API.BASE + PRODUCT_TOP_SALES)
    public ResponseEntity<List<ProductFilterResponse>> topSales(){
        return ResponseEntity.ok(service.topSales());
    }

    @GetMapping(PRODUCT_ARRIVAL)
    @Tag(name = Router.API.BASE + PRODUCT_ARRIVAL)
    public ResponseEntity<List<ProductFilterResponse>> productArrival(){
        return ResponseEntity.ok(service.newArrival());
    }
}
