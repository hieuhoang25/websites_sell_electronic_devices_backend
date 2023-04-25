package com.poly.datn.controller;

import static com.poly.datn.controller.router.Router.CHECK_OUT_API.CHECKOUT;
import static com.poly.datn.controller.router.Router.USER_API.BASE;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.poly.datn.dto.request.CheckOutRequest;
import com.poly.datn.entity.User;
import com.poly.datn.exception.InventoryException;
import com.poly.datn.repository.AccountRepository;
import com.poly.datn.service.CheckOutService;
import com.poly.datn.service.UserInfoByTokenService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping(BASE + CHECKOUT)
@RequiredArgsConstructor
public class CheckoutController {

  final CheckOutService checkoutService;

  final OrderTrackingByIdController orderService;

  final AccountRepository accountRepository;
  final UserInfoByTokenService userInfoService;

  public ResponseEntity<?> checkoutByUserId(@PathVariable Integer userId, @Valid @RequestBody CheckOutRequest request) {

    Integer trackId;
    try {
      trackId = checkoutService.checkout(userId, request);
      return trackId < 0 ? ResponseEntity.badRequest().body("Error: Can't process checkout")
          : ResponseEntity.ok().body(orderService.getTrackingOrderById(trackId));
    } catch (Exception e) {
      if (e instanceof IllegalArgumentException) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
      } else if (e instanceof InventoryException) {
        return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(e.getMessage());
      } else
        return ResponseEntity.badRequest().body(e.getMessage());
    }

  }

  @PostMapping
  public ResponseEntity<?> checkoutCurrentUser(@Valid @RequestBody CheckOutRequest request) {

    try {
      User user = userInfoService.getCurrentUser();
      return checkoutByUserId(user.getId(), request);
    } catch (Exception e) {
      if (e instanceof IllegalArgumentException) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
      } else if (e instanceof InventoryException) {
        return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(e.getMessage());
      } else
        return ResponseEntity.badRequest().body(e.getMessage());
    }
  }

}
