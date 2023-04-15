package com.poly.datn.controller;

import static com.poly.datn.controller.router.Router.API.BASE;
import static com.poly.datn.controller.router.Router.CART_API.CART;
import static com.poly.datn.controller.router.Router.CART_API.CART_ITEMS;
import static com.poly.datn.dto.response.CartDetailResponse.withCartPrice_Detail;
import static com.poly.datn.dto.response.CartDetailResponse.withVariantDiscount_amount;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.poly.datn.dto.request.CartDetailAnnonRequest;
import com.poly.datn.dto.request.CartDetailRequest;
import com.poly.datn.dto.response.CartDetailResponse;
import com.poly.datn.dto.response.CartDetailResponse.CartDetailResponseBuilder;
import com.poly.datn.dto.response.CartResponse;
import com.poly.datn.dto.response.CartResponse.CartResponseBuilder;
import com.poly.datn.dto.response.ProductVariantResponse;
import com.poly.datn.exception.cart.VariantUnavailable;
import com.poly.datn.service.CartDetailService;
import com.poly.datn.service.CartService;
import com.poly.datn.service.ProductVariantService;
import com.poly.datn.service.UserInfoByTokenService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping(BASE + CART)
@RequiredArgsConstructor
@Validated
@Tag(name = "Cart for guest ", description = "CART AND DETAIL FOR ANNON")
public class CartForAnnonController {

    final CartDetailService detailService;
    final CartService cartService;
    final UserInfoByTokenService userInfoService;
    final ProductVariantService variantService;

    @Operation(summary = "get cart item for guest cart")
    @PostMapping(CART_ITEMS)
    public ResponseEntity<?> getCartDetailInfo(@Valid @RequestBody CartDetailAnnonRequest request) {
        Integer cartDetailId = 101;
        Integer quantity = request.getQuantity();
        CartDetailResponseBuilder respone = CartDetailResponse.getPlainCartDetailResponeBuilder(cartDetailId);
        try {
            ProductVariantResponse variant = variantService.findById(request.getVariant_id());
            respone.withProductVariant(variant).withQuantity(quantity) ;
            withVariantDiscount_amount(respone);
            withCartPrice_Detail(respone);

            CartDetailResponse obj = respone.build();
            
            log.info("respone: price_detail " + obj.getPrice_detail() + " respone quantity: " + 
            obj.getQuantity());
            return ResponseEntity.ok().body(obj);
        } catch (Exception e) {
            e.printStackTrace();
            if (e instanceof EntityNotFoundException) {
                return ResponseEntity.badRequest().body("Can't found variant");
            }else if(e instanceof VariantUnavailable) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Variant is Unavailable ");
            }
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("something went wrong");
   }

    @Operation(summary = "get new cart response for guest")
    @GetMapping({ "" })
    public ResponseEntity<?> getNewAnnonCart() {
        try {
            Integer id = ThreadLocalRandom.current().nextInt(1, (Integer.MAX_VALUE - 1));
            log.info("random id for guest'cart: " + id);
            CartResponseBuilder cartBuilder = CartResponse.getAnnonCartResponseBuilder(id);
            return ResponseEntity.ok().body(cartBuilder.build());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @PostMapping("/update")
    public ResponseEntity<?> updateGuestCart(@RequestBody List<CartDetailRequest> requestList) {

        try {
            if(requestList == null) throw new RuntimeException("list is null");
            
            CartResponse res = CartResponse.getAnnonCartResponseBuilder(cartService.getRandomId()).build();
            if(requestList.size() == 0) return ResponseEntity.ok().body(res);

            Integer cartId = requestList.get(0).getCart_id();            
            requestList.removeIf(t -> t == null);
    
            CartResponse response =  cartService.updateGuestCart(cartId, requestList);
            return ResponseEntity.ok(response);
        }catch(Exception e) {
            log.info("Updated cart error");
            e.printStackTrace();
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
