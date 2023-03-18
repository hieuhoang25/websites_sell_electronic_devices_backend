package com.poly.datn.controller;

import com.poly.datn.dto.response.ProductStorageResponse;
import com.poly.datn.service.ProductStorageService;
import io.swagger.v3.oas.annotations.tags.Tag;
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
import static com.poly.datn.controller.router.Router.API.PRODUCT_STORAGE;

@RestController
@RequestMapping(BASE + PRODUCT_STORAGE)
@RequiredArgsConstructor
@Validated
@Tag(name = BASE + PRODUCT_STORAGE)
public class ProductStorageController {
    private final ProductStorageService productStorageService;

    @GetMapping("/{productId}/{colorId}")
    public ResponseEntity<List<ProductStorageResponse>>
    getProductStorageService(@PathVariable @Valid @NotNull Integer productId,
                             @PathVariable @Valid @NotNull Integer colorId) {
        return ResponseEntity.ok(productStorageService.getProductStorage(productId, colorId));
    }
}
