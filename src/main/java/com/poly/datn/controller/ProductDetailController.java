package com.poly.datn.controller;

import com.poly.datn.dto.request.ProductDetailRequest;
import com.poly.datn.dto.response.ProductDetailResponse;
import com.poly.datn.service.ProductDetail;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

import static com.poly.datn.controller.router.Router.USER_API.BASE;
import static com.poly.datn.controller.router.Router.USER_API.PRODUCT_DETAIL;

@RestController
@RequestMapping(BASE)
@RequiredArgsConstructor
public class ProductDetailController {
    private final ProductDetail productDetail;

    @GetMapping(PRODUCT_DETAIL)
    ResponseEntity<ProductDetailResponse> getProductDetail(@Valid @RequestBody ProductDetailRequest request) {
        ProductDetailResponse productDetailResponse = productDetail.getProductDetail(request);
        return ResponseEntity.ok(productDetailResponse);
    }
}
