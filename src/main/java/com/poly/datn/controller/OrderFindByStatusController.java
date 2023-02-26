package com.poly.datn.controller;

import com.poly.datn.controller.router.Router;
import com.poly.datn.dto.response.OrderResponse;
import com.poly.datn.service.OrderFindByStatus;
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

import static com.poly.datn.controller.router.Router.ADMIN_API.ORDER;

@RequiredArgsConstructor
@RestController
@RequestMapping(Router.ADMIN_API.BASE)
@Validated
public class OrderFindByStatusController {
    private final OrderFindByStatus service;

    @GetMapping(ORDER + "/{idStatus}")
    @Tag(name = "admin/order")
    public ResponseEntity<List<OrderResponse>> getOrder(
            @PathVariable @Valid @NotNull(message = "Không để trống tình trạng đơn hàng") Integer idStatus) {
        List<OrderResponse> orderResponse = service.getOrder(idStatus);
        if (orderResponse.isEmpty())
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(orderResponse);
    }
}
