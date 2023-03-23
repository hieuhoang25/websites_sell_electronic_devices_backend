package com.poly.datn.controller;

import com.poly.datn.controller.router.Router;
import com.poly.datn.dto.request.RatingProductRequest;
import com.poly.datn.dto.response.ProductRatingResponse;
import com.poly.datn.service.RatingProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import static com.poly.datn.controller.router.Router.USER_API.IS_RATING;

@RequiredArgsConstructor
@RestController
@RequestMapping(Router.USER_API.BASE)
@Validated
public class RatingProductController {
    private final RatingProductService service;
    @PostMapping
    public ResponseEntity<ProductRatingResponse> rateProduct(@RequestBody @Valid RatingProductRequest request){
        return ResponseEntity.ok(service.rateProduct(request));
    }

    @GetMapping(IS_RATING)
    public ResponseEntity<Boolean> isRating(@RequestParam @Valid @NotNull Integer productId,
                                            @RequestParam @Valid @NotNull Integer orderDetailId){
        return ResponseEntity.ok(service.isRating(productId,orderDetailId));
    }
}
