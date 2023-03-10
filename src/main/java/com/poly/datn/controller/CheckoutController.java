package com.poly.datn.controller;

import static com.poly.datn.controller.router.Router.CHECK_OUT_API.AUTH_VAR;
import static com.poly.datn.controller.router.Router.CHECK_OUT_API.BASE;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.poly.datn.dto.request.CheckOutRequest;
import com.poly.datn.entity.Account;
import com.poly.datn.repository.AccountRepository;
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

    final AccountRepository accountRepository;

   
    public ResponseEntity<?>checkoutByUserId(@PathVariable Integer userId, @Valid @RequestBody CheckOutRequest request) {
        
       
        Integer trackId = checkoutService.checkout(userId, request);
        
        return trackId < 0? ResponseEntity.badRequest().body("Error: Can't process checkout") : ResponseEntity.ok().body(orderService.getTrackingOrderById(trackId));
    }

    @PostMapping
    public ResponseEntity<?> checkoutCurrentUser(@Valid @RequestBody CheckOutRequest request) {

        String name = SecurityContextHolder.getContext().getAuthentication().getName();
        Account account = accountRepository.findByUsername(name);
        return checkoutByUserId(account.getId(), request);

    }
    
}
