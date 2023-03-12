package com.poly.datn.controller;

import static com.poly.datn.controller.router.Router.USER_API.BASE;
import static com.poly.datn.controller.router.Router.USER_API.WISHLIST;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.poly.datn.dto.request.WishlistRequest;
import com.poly.datn.service.WishlistService;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(BASE + WISHLIST)
@RequiredArgsConstructor
@Tag(name = "User's wishlist API", description = "CRUD for user's wishlist")
public class WishlistController {
    
    final WishlistService wishlistService;


    @GetMapping
    public ResponseEntity<?> getCurrentUserWishlist() {
        return ResponseEntity.ok().body(wishlistService.getWishlistOfCurrentUser());
    }

    @PutMapping
    public ResponseEntity<?> addProductToWishlist(List<WishlistRequest> request) {
        return ResponseEntity.ok().body(wishlistService.addProductToWishlist(null));
    }
}
