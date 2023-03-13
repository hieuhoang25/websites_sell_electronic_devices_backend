package com.poly.datn.controller;

import static com.poly.datn.controller.router.Router.CART_API.*;
import static com.poly.datn.controller.router.Router.API.BASE;
import static com.poly.datn.controller.router.Router.CART_API.CART_DETAIL;
import static com.poly.datn.controller.router.Router.CART_API.CART_DETAIL_TAG_NAME;
import static com.poly.datn.controller.router.Router.CART_API.CART_ID_VAR;
import static com.poly.datn.controller.router.Router.CART_API.CART_ITEMS;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
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
import com.poly.datn.service.CartService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping(BASE + CART)
@RequiredArgsConstructor
@Tag(name = CART_DETAIL_TAG_NAME, description = "CRUD FOR CartDetail")
public class CartDetailController {

    final CartDetailService detailService;
    final CartService cartService;
    
    @Operation(summary ="Find all items in cart", description = "Find all CartDetail by Cart_Id")
    @GetMapping(CART_ID_VAR + CART_ITEMS)
    public ResponseEntity<?> getCartItemsByCartId(@PathVariable Integer cartId) {
        return ResponseEntity.ok(detailService.findAllByCartId(cartId));
    }

  @Operation(summary = "Find item in cart by id", description = "Find CartDetail in cart by passing cart_id (cartId) and cart_detail_id (itemId) ")
    @GetMapping(CART_ID_VAR + CART_DETAIL + "/{itemId}")
    public ResponseEntity<CartDetailResponse> getOndeCartDetail(@PathVariable("cartId") Integer cartId, @PathVariable Integer itemId) {
        return ResponseEntity.ok(detailService.findByCartId(cartId, itemId));
    }


    @Operation(summary = "Find all items in cart of current user")
    @GetMapping(CART_ITEMS)
    public ResponseEntity<?> getCartItemsOfCurrentUser() {
        return getCartItemsByCartId(cartService.getCurrentUser().getCarts().getId());
    }

   
    @PostMapping(CART_ID_VAR + CART_DETAIL)
    public ResponseEntity<?> addOneCartDetail(@PathVariable Integer cartId, @Valid @RequestBody CartItemRequest request) {
        
        validateURLBody(cartId, request.getCart_id());
        try {  
            CartDetailResponse response = detailService.add(request);
            return new ResponseEntity<>(response == null? "Can't add Item (variant may not exists)" : response, response == null? HttpStatus.BAD_REQUEST : HttpStatus.OK);
        }catch(Exception e) {
            e.printStackTrace();
        }
        return null;
      
    }


    @PutMapping(CART_ID_VAR + CART_DETAIL)
    public ResponseEntity<?> updateOneCartDetail(@PathVariable Integer cartId,@Valid  @RequestBody CartDetailRequest request) {
       
        validateURLBody(cartId, request.getCart_id());
        
        CartDetailResponse response = detailService.update(cartId,request);
        HttpStatus code = response.getId() == -1? HttpStatus.BAD_REQUEST : HttpStatus.OK;
        return new ResponseEntity<>(response.getId() == -1? "Item's is deleted due to variant is unvailable" : response, code);
    }

    @DeleteMapping(CART_ID_VAR + CART_DETAIL)
    public ResponseEntity<?> deleteOneCartDetail(@PathVariable Integer cartId, @Valid @RequestBody CartDetailRequest request) {
        validateURLBody(cartId, request.getCart_id());
        detailService.delete(request);
        return getCartItemsByCartId(cartId);
    }


    // ! unesscary?
    @GetMapping(CART_DETAIL + "/{itemId}")
    public ResponseEntity<CartDetailResponse> getOndeCartDetail(@PathVariable Integer itemId) {
        return ResponseEntity.ok(detailService.findCartDetailById(itemId));
    }


    private void validateURLBody(Integer cartId, Integer requestCartId) {
        if(!requestCartId.equals(cartId)) {
            throw new RuntimeException("url cart doesn't match request body value");
        }
    }

 

}
