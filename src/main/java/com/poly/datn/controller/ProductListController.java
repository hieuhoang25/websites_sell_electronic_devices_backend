package com.poly.datn.controller;

import com.poly.datn.common.MessageResponse;
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
import com.poly.datn.common.ResponseBody;

import java.util.Optional;

import static com.poly.datn.controller.router.Router.PRODUCT_API.BASE;
import static com.poly.datn.controller.router.Router.USER_API.PRODUCT;

@RequiredArgsConstructor
@RequestMapping(BASE)
@RestController
public class ProductListController {
    private final ProductList productList;

    @GetMapping(PRODUCT)
    @Tag(name = "Product")
    public ResponseEntity<ResponseBody<SearchResult<ProductListResponse>>> productListResponse(
            @RequestParam("size") Optional<Integer> size,
            @RequestParam("page") Optional<Integer> page) {
        Pageable pageable = PageRequest.of(page.orElse(1), size.orElse(1));
        return ResponseEntity.ok(
                new ResponseBody<>(
                        1,
                        MessageResponse.MESSAGE_SUCCESS,
                        productList.getAllProducts(pageable)
                )
        );
    }

}
