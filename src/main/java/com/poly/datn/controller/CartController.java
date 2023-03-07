package com.poly.datn.controller;


import static com.poly.datn.controller.router.Router.CART_API.BASE;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.poly.datn.service.CartService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping(BASE)
@RequiredArgsConstructor
@Slf4j
public class CartController {

    final CartService service;
    
    
    @GetMapping("/user/{userId}")
    public ResponseEntity<?> getCartByUser(@PathVariable Integer userId) {
        return ResponseEntity.ok(service.findByUserId(userId)) ;
    }

    @GetMapping("/{cartId}")
    public ResponseEntity<?> getCartById(@PathVariable Integer cartId) {
        return ResponseEntity.ok(service.findByCartId(cartId));
    }

    @DeleteMapping("/{cartId}")
    public ResponseEntity<?> removeAllItemsInCart(@PathVariable Integer cartId) {

       boolean deleted =  service.deleteAllItemsInCart(cartId);
       return new ResponseEntity<Object>(String.format("%s", deleted? "Clear cart" : "Cart is empty"),  deleted? HttpStatus.OK : HttpStatus.BAD_REQUEST);
    }

    
}
