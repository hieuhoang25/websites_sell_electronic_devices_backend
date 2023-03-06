package com.poly.datn.controller;

import static com.poly.datn.controller.router.Router.USER_API.BASE;
import static com.poly.datn.controller.router.Router.USER_API.ORDER;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.rpc.context.AttributeContext.Response;
import com.poly.datn.dto.response.OrderResponse;
import com.poly.datn.service.OrderTrackingByIdService;

import lombok.AllArgsConstructor;


@RestController
@RequestMapping(BASE + ORDER)
@AllArgsConstructor
public class OrderTrackingByIdController {

    private final OrderTrackingByIdService orderServerice;
   
    @GetMapping("/id" + "/{orderId}") 
    public ResponseEntity<?>getTrackingOrderById(@PathVariable Integer orderId) {
        return ResponseEntity.ok(orderServerice.findByOrderId(orderId));  
    }   
    
    
}
