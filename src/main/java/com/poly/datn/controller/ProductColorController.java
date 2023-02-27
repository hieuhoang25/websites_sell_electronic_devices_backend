package com.poly.datn.controller;

import com.poly.datn.dto.response.ProductColorResponse;
import com.poly.datn.service.ProductColorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

import static com.poly.datn.controller.router.Router.API.BASE;
import static com.poly.datn.controller.router.Router.API.PRODUCT;

@RestController
@RequestMapping(BASE)
@RequiredArgsConstructor
@Validated
public class ProductColorController {
    private final ProductColorService productColorService;

    @GetMapping(PRODUCT + "-color/{productId}")
    public ResponseEntity<List<ProductColorResponse>>
    getProductColor(@PathVariable @Valid @NotNull Integer productId) {
        return ResponseEntity.ok(productColorService.getProductColor(productId));
    }
}
