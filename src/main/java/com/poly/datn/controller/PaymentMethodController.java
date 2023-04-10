package com.poly.datn.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.rpc.context.AttributeContext.Response;
import com.poly.datn.service.CheckOutService;

import lombok.RequiredArgsConstructor;
import static com.poly.datn.controller.router.Router.API.BASE;
import static com.poly.datn.controller.router.Router.API.PAY_METHOD;

@RestController
@RequiredArgsConstructor
@RequestMapping(BASE + PAY_METHOD)
public class PaymentMethodController {

    private final CheckOutService checkOutService;

    
    @GetMapping
    public ResponseEntity<?> getPaymentMethod() {
        return ResponseEntity.ok().body(checkOutService.getPaymentMethod());  
    } 
    
}
