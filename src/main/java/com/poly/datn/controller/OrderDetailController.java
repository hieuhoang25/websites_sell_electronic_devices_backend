package com.poly.datn.controller;

import com.poly.datn.controller.router.Router;
import com.poly.datn.dto.response.OrderDetailResponse;
import com.poly.datn.service.OrderDetailService;
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

import static com.poly.datn.controller.router.Router.ADMIN_API.ORDER_DETAIL;

@RequestMapping(Router.ADMIN_API.BASE + ORDER_DETAIL)
@RestController
@RequiredArgsConstructor
@Validated
@Tag(name = Router.ADMIN_API.BASE + ORDER_DETAIL)
public class OrderDetailController {
    private final OrderDetailService service;

    @GetMapping( "/{idOrder}")
    public ResponseEntity<List<OrderDetailResponse>> getOrderDetail(
            @PathVariable @Valid @NotNull(message = "idOrder can not be null") Integer idOrder) {
        List<OrderDetailResponse> orderDetailResponses = service.getOrderDetail(idOrder);
        if (orderDetailResponses.isEmpty())
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(orderDetailResponses);
    }
}
