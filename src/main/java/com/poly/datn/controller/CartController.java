package com.poly.datn.controller;


import static com.poly.datn.controller.router.Router.CART_API.BASE;
import static com.poly.datn.controller.router.Router.CART_API.USER_CART;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.poly.datn.dto.response.CartResponse;
import com.poly.datn.service.CartService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping(BASE)
@RequiredArgsConstructor
@Slf4j
public class CartController {


    final CartService service;
    
    @GetMapping(USER_CART)
    public ResponseEntity<?> getCartByUser(@PathVariable Integer userId) {
        log.info("controller");
        return ResponseEntity.ok(service.findByUserId(userId)) ;
    }

    @GetMapping
    public String getSwagger() {
        return "nulc";
    }
    
    
}
