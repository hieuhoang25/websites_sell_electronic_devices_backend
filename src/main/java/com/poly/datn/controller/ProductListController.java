package com.poly.datn.controller;

import com.poly.datn.common.SearchResult;
import com.poly.datn.dto.response.ProductListResponse;
import com.poly.datn.service.ProductList;
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

import static com.poly.datn.controller.router.Router.API.BASE;
import static com.poly.datn.controller.router.Router.API.PRODUCT;

;

@RequiredArgsConstructor
@RequestMapping(BASE + PRODUCT)
@RestController
@Tag(name = BASE + PRODUCT)
public class ProductListController {
    private final ProductList productList;

    @GetMapping()
    public ResponseEntity<SearchResult<ProductListResponse>> productListResponse(
            @RequestParam("size") Optional<Integer> size,
            @RequestParam("page") Optional<Integer> page) {
        Pageable pageable = PageRequest.of(page.orElse(1), size.orElse(1));
        return ResponseEntity.ok(productList.getAllProducts(pageable));
    }

}
