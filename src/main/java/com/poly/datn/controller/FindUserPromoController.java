package com.poly.datn.controller;

import static com.poly.datn.controller.router.Router.API.USER_PROMO;
// import static com.poly.datn.controller.router.Router.API.BASE;
import static com.poly.datn.controller.router.Router.USER_API.BASE;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.poly.datn.dto.request.FindAllUserPromoRequest;
import com.poly.datn.dto.response.FindAllUserPromoResponse;
import com.poly.datn.service.FindAllUserPromotionService;
import com.poly.datn.service.UserInfoByTokenService;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping(BASE + USER_PROMO)
@Tag(name = "Find user promo (client)")
@RequiredArgsConstructor
@Slf4j
public class FindUserPromoController {

   
    private final FindAllUserPromotionService promoService;
    private final UserInfoByTokenService userService;

    @GetMapping("/list")
    public ResponseEntity<?> getPromoOfUser() {
        try {
            List<FindAllUserPromoResponse> promoUser = promoService.findAllUserPromo(userService.getCurrentUser().getId());
            
            if(promoUser == null)  return ResponseEntity.badRequest().body("Can't find any promo");
            return ResponseEntity.ok().body(promoUser);
        } catch (Exception e) {
           log.info(e.getMessage());
            return ResponseEntity.badRequest().body(e.getMessage());
        }
        
    }

    @GetMapping("/valid")
    public ResponseEntity<?> getValidPromoOfUser() {
        try {
            List<FindAllUserPromoResponse> promoUser = promoService.findAllValidUserPromo(userService.getCurrentUser().getId());
            if(promoUser == null)  return ResponseEntity.badRequest().body("Can't find any promo");
            return ResponseEntity.ok().body(promoUser);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(e.getMessage());
        } 
    }

    @PostMapping("/order")
    public  ResponseEntity<?> getPromoForOrderTotal(@RequestBody FindAllUserPromoRequest request ) {
        try {
            List<FindAllUserPromoResponse> promoUser = promoService.findPromoForOrder(userService.getCurrentUser().getId(), request);
            if(promoUser == null)  return ResponseEntity.badRequest().body("Can't find any promo");
            return ResponseEntity.ok().body(promoUser);
        } catch (Exception e) {
            log.info(e.getMessage());
            return ResponseEntity.badRequest().body(e.getMessage());
        } 
        // return ResponseEntity.ok().body("null");
    }

    @PostMapping("/coupon")
    public ResponseEntity<?> checkCode(@RequestBody FindAllUserPromoRequest request) {
        try {
            List<FindAllUserPromoResponse> promoUser = promoService.findPromoByCode(userService.getCurrentUser().getId(),request);
            if(promoUser == null)  return ResponseEntity.badRequest().body("Can't find any promo");
            return ResponseEntity.ok().body(promoUser);
        } catch (Exception e) {
            log.info(e.getMessage());
            // e.printStackTrace();
            return ResponseEntity.badRequest().body(e.getMessage());
        } 
    }

}
