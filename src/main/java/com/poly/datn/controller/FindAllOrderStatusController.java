package com.poly.datn.controller;

import com.poly.datn.controller.router.Router;
import com.poly.datn.dto.response.OrderStatusResponse;
import com.poly.datn.service.CRUDOrderStatusService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.poly.datn.controller.router.Router.API.ORDER_STATUS;

@RequiredArgsConstructor
@RestController
@RequestMapping(Router.API.BASE + ORDER_STATUS)
public class FindAllOrderStatusController {
    private final CRUDOrderStatusService service;

    @GetMapping
    public ResponseEntity<List<OrderStatusResponse>> getOrderStatus(){
        return ResponseEntity.ok(service.getOrderStatus());
    }
}
