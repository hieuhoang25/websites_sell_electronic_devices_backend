package com.poly.datn.controller;

import static com.poly.datn.controller.router.Router.CART_API.*;


import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.poly.datn.dto.request.CartDetailRequest;
import com.poly.datn.dto.request.CartItemRequest;
import com.poly.datn.dto.response.CartDetailResponse;
import com.poly.datn.service.CartDetailService;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping(BASE)
@RequiredArgsConstructor
@Tag(name = CART_DETAIL_TAG_NAME, description = "CRUD FOR CartDetail")
public class CartDetailController {

    final CartDetailService detailService;
    
    @Tag(name= "Get all CartDetail by Cart_Id")
    @GetMapping(CART_ID_VAR + CART_ITEMS)
    public ResponseEntity<?> getCartItemsByCartId(@PathVariable Integer cartId) {
        return ResponseEntity.ok(detailService.findAllByCartId(cartId));
    }

  
    @GetMapping(CART_ID_VAR + CART_DETAIL + "/{itemId}")
    public ResponseEntity<CartDetailResponse> getOndeCartDetail(@PathVariable("cartId") Integer cartId, @PathVariable Integer itemId) {
        return ResponseEntity.ok(detailService.findByCartId(cartId, itemId));
    }

    @GetMapping(CART_DETAIL + "/{itemId}")
    public ResponseEntity<CartDetailResponse> getOndeCartDetail(@PathVariable Integer itemId) {
        return ResponseEntity.ok(detailService.findCartDetailById(itemId));
    }

    @PutMapping(CART_ID_VAR + CART_DETAIL)
    public ResponseEntity<?> updateOneCartDetail(@PathVariable Integer cartId,@Valid  @RequestBody CartDetailRequest request) {
       
        validateURLBody(cartId, request.getCart_id());
        return ResponseEntity.ok(detailService.update(cartId,request));
    }

    @PostMapping(CART_ID_VAR + CART_DETAIL)
    public ResponseEntity<?> addOneCartDetail(@PathVariable Integer cartId, @Valid @RequestBody CartItemRequest request) {
        validateURLBody(cartId, request.getCart_id());
        return ResponseEntity.ok(detailService.add(request));
    }

    private void validateURLBody(Integer cartId, Integer requestCartId) {
        if(!requestCartId.equals(cartId)) {
            throw new RuntimeException("url cart doesn't match request body value");
        }
    }

    @DeleteMapping(CART_ID_VAR + CART_DETAIL)
    public ResponseEntity<?> deleteOneCartDetail(@PathVariable Integer cartId, @Valid @RequestBody CartDetailRequest request) {
        validateURLBody(cartId, request.getCart_id());
        detailService.delete(request);
        return getCartItemsByCartId(cartId);
    }
}
