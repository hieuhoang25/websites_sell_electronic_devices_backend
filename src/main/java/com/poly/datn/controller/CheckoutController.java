package com.poly.datn.controller;

import static com.poly.datn.controller.router.Router.CHECK_OUT_API.AUTH_VAR;
import static com.poly.datn.controller.router.Router.CHECK_OUT_API.BASE;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.poly.datn.dto.request.CheckOutRequest;
import com.poly.datn.service.CheckOutService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping(BASE)
@RequiredArgsConstructor
public class CheckoutController {

    final CheckOutService checkoutService;

    final  OrderTrackingByIdController orderService;

    @PostMapping(AUTH_VAR) 
    public ResponseEntity<?>checkoutByUserId(@PathVariable Integer userId, @Valid @RequestBody CheckOutRequest request) {
        
         if(!userId.equals(request.getUser_id())) return ResponseEntity.badRequest().body("url, userId unmatched");
       
        Integer trackId = checkoutService.checkout(userId, request);
        
        return trackId < 0? ResponseEntity.badRequest().body("Error: Can't process checkout") : ResponseEntity.ok(orderService.getTrackingOrderById(trackId));
    }
    
}
