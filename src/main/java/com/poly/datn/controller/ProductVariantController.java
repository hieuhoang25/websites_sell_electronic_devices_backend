package com.poly.datn.controller;

import com.poly.datn.dto.request.ProductVariantRequest;
import com.poly.datn.dto.response.ProductVariantResponse;
import com.poly.datn.service.ProductVariantService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.Optional;

import static com.poly.datn.controller.router.Router.ADMIN_API.BASE;
import static com.poly.datn.controller.router.Router.ADMIN_API.PRODUCT_VARIANT;
@RestController
@RequestMapping(BASE + PRODUCT_VARIANT)
@RequiredArgsConstructor
public class ProductVariantController {
    private final ProductVariantService productVariantService;

    @GetMapping("/{productId}")
    public ResponseEntity<?> findByProductId(@RequestParam("size") Optional<Integer> size,
                                             @RequestParam("page") Optional<Integer> page,
                                             @PathVariable("productId") Integer id){
        Pageable pageable = PageRequest.of(page.orElse(0),size.orElse(10));
        return ResponseEntity.ok(productVariantService.findByProductId(pageable,id));
    }
    @PostMapping()
    public ResponseEntity<?> createProductVariant(@RequestBody ProductVariantRequest productVariantRequest){
        productVariantRequest.setStatus(true);
        return ResponseEntity.ok(productVariantService.create(productVariantRequest));
    }
    @PutMapping()
    public ResponseEntity<?> updateProductVariant(@RequestBody ProductVariantRequest productVariantRequest){
        productVariantRequest.setStatus(true);
        return ResponseEntity.ok(productVariantService.update(productVariantRequest));
    }



}
