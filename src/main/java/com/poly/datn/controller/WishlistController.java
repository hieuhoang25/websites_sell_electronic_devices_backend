package com.poly.datn.controller;

import static com.poly.datn.controller.router.Router.USER_API.BASE;
import static com.poly.datn.controller.router.Router.USER_API.WISHLIST;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.poly.datn.dto.request.WishlistRequest;
import com.poly.datn.service.UserInfoByTokenService;
import com.poly.datn.service.WishlistService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@RequestMapping(BASE + WISHLIST)
@RequiredArgsConstructor
@Validated
@Tag(name = "User's wishlist API", description = "CRUD for user's wishlist")
public class WishlistController {
    
    final WishlistService wishlistService;
    final UserInfoByTokenService currentUserService;

    @GetMapping
    @Operation(summary = "Get current user's wishlists", description = "Get logged in user's wishlists, return Unauthorized if user've logged in")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200",description = "Retrieve current user's wishlists"),
        @ApiResponse(responseCode = "401",description = "Unauthorized user"),
    })
    public ResponseEntity<?> getCurrentUserWishlist() {
        // if(!isUserLoggedIn()) return getUnAuthorizedResponse();
        log.info("request wishlist");

        return ResponseEntity.ok().body(wishlistService.getWishlistOfCurrentUser());
    }

    @GetMapping("/items")
    public ResponseEntity<?> getUserWishlist( @RequestParam Optional<Integer> size,
    @RequestParam Optional<Integer> page) {
        log.info("request wishlist");
        
        Pageable pageable = PageRequest.of(page.orElse(0),size.orElse(6));
return ResponseEntity.ok().body(wishlistService.getWishlistOfCurrentUser(pageable));


        
        // return ResponseEntity.ok().body(re);
        // return null;


    }

    @PostMapping
    @Operation(summary = "Add product to user's wishlist" )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200",
                     description = "Retrieve user's wishlists"),
        @ApiResponse(responseCode = "400",
                     description = "Add product to wishlist FAILED, product's already in wishlists")
    })
    public ResponseEntity<?> addProductToWishlist(@RequestBody List<WishlistRequest> request) {
        // if(!isUserLoggedIn()) return getUnAuthorizedResponse();
       
        return  ResponseEntity.ok().body(wishlistService.addProductToWishlist(request));
    }

    @Operation(summary = "Remove products in current user's wishlist")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200",
                     description = "Retrieve user's wishlists after removed"),
        @ApiResponse(responseCode = "400",
                     description = "Remove products from wishlist FAILED, product not found")
    })
    @DeleteMapping
    public ResponseEntity<?> deleteProductFromWishlist(@RequestBody List<WishlistRequest> requests) {
        // if(!isUserLoggedIn()) return getUnAuthorizedResponse();

        return ResponseEntity.ok().body(wishlistService.deleteProductFromWishlist(requests));
    }

    @GetMapping("{productId}")
    public ResponseEntity<Boolean> isWishlistOfUserExists(@PathVariable @Valid @NotNull Integer productId){
        return ResponseEntity.ok(wishlistService.findWishlistOfUserByProductId(productId));
    }

  
}
