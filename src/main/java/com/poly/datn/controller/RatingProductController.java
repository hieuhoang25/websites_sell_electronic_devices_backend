package com.poly.datn.controller;

import com.poly.datn.controller.router.Router;
import com.poly.datn.dto.request.RatingProductRequest;
import com.poly.datn.dto.response.OrderDetailResponse;
import com.poly.datn.dto.response.ProductRatingResponse;
import com.poly.datn.service.RatingProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static com.poly.datn.controller.router.Router.USER_API.IS_RATING;
import static com.poly.datn.controller.router.Router.USER_API.RATING;

@RequiredArgsConstructor
@RestController
@RequestMapping(Router.USER_API.BASE)
@Validated
public class RatingProductController {
    private final RatingProductService service;
    @PostMapping(RATING)
    public ResponseEntity rateProduct(
            @RequestBody  List<@Valid RatingProductRequest> request){
        service.rateProduct(request);
        return ResponseEntity.noContent().build();
    }

    @GetMapping(IS_RATING)
    public ResponseEntity<List<OrderDetailResponse>> isRating(
            @RequestParam Integer orderId){
        return ResponseEntity.ok(service.isRating(orderId));
    }
}
