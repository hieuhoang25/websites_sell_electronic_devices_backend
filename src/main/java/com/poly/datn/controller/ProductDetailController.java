package com.poly.datn.controller;

import com.poly.datn.dto.request.ProductDetailRequest;
import com.poly.datn.dto.response.ProductDetailResponse;
import com.poly.datn.service.ProductDetailService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.validation.Valid;
import static com.poly.datn.controller.router.Router.API.*;

@RestController
@RequestMapping(BASE)
@RequiredArgsConstructor
public class ProductDetailController {
    private final ProductDetailService productDetailService;

    @GetMapping(PRODUCT + "-detail")
    ResponseEntity<ProductDetailResponse> getProductDetailService(@Valid @RequestBody ProductDetailRequest request) {
        ProductDetailResponse productDetailResponse = productDetailService.getProductDetail(request);
        return ResponseEntity.ok(productDetailResponse);
    }
}
