package com.poly.datn.controller;

import com.poly.datn.controller.router.Router;
import com.poly.datn.dto.response.OrderTrackingResponse;
import com.poly.datn.service.OrderTrackingService;
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

import static com.poly.datn.controller.router.Router.USER_API.ORDER_TRACKING;

@RequiredArgsConstructor
@RestController
@RequestMapping(Router.USER_API.BASE + ORDER_TRACKING)
@Validated
@Tag(name = Router.USER_API.BASE + ORDER_TRACKING)
public class OrderTrackingController {
    private final OrderTrackingService service;

    @GetMapping("/{statusId}")
    public ResponseEntity<List<OrderTrackingResponse>> getUserOrdered(
            @PathVariable @Valid @NotNull(message = "Order status can not be null") Integer statusId) {
        List<OrderTrackingResponse> orderTrackingResponses = service.getUserOrdered(statusId);
        return ResponseEntity.ok(orderTrackingResponses);
    }
}
