package com.poly.datn.controller;

import com.poly.datn.controller.router.Router;
import com.poly.datn.dto.response.OrderTrackingResponse;
import com.poly.datn.dto.response.PaginationOrderTracking;
import com.poly.datn.service.OrderTrackingService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.Optional;


import static com.poly.datn.controller.router.Router.USER_API.ORDER_TRACKING;

@RequiredArgsConstructor
@RestController
@RequestMapping(Router.USER_API.BASE + ORDER_TRACKING)
@Validated
@Tag(name = Router.USER_API.BASE + ORDER_TRACKING)
public class OrderTrackingController {
    private final OrderTrackingService service;

    @GetMapping("/{statusId}")
    public ResponseEntity<PaginationOrderTracking<OrderTrackingResponse>> getUserOrdered(
            @PathVariable @Valid @NotNull(message = "Order status can not be null") Integer statusId,
            @RequestParam Optional<Integer> size,
            @RequestParam Optional<Integer> page) {
        PageRequest pageRequest =
                PageRequest.of(
                        page.orElse(0),size.orElse(5),
                        Sort.by("createdDate").descending()
                );
        PaginationOrderTracking<OrderTrackingResponse> orderTrackingResponses =
                service.getUserOrdered(statusId, pageRequest);
        return ResponseEntity.ok(orderTrackingResponses);
    }
}
