package com.poly.datn.service.serviceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.poly.datn.common.mapper.ModelConverter;
import com.poly.datn.dto.request.WishlistRequest;
import com.poly.datn.dto.response.WishlistResponse;
import com.poly.datn.entity.Product;
import com.poly.datn.entity.User;
import com.poly.datn.entity.Wishlist;
import com.poly.datn.entity.Wishlist.WishlistBuilder;
import com.poly.datn.repository.ProductRepository;
import com.poly.datn.repository.WishlistRepository;
import com.poly.datn.service.UserInfoByTokenService;
import com.poly.datn.service.WishlistService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(rollbackOn = Exception.class)
public class WishlistServiceImpl implements WishlistService{


    final ModelConverter converter;
    final WishlistRepository wishlistRepository;
    final UserInfoByTokenService userService;
    final ProductRepository productRepository;

    @Override
    public List<WishlistResponse> addProductToWishlist(List<WishlistRequest> request) {
      List<Wishlist> wishlists = convertRequestToEntity(getCurrentUser(), request);
        return converter.mapAllByIterator( wishlistRepository.saveAll(wishlists), WishlistResponse.class);
    }

    @Override
    public List<WishlistResponse> deleteProductFromWishlist(List<WishlistRequest> productId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteProductFromWishlist'");
    }

    @Override
    public List<WishlistResponse> getWishlistOfCurrentUser() {
       return converter.mapAllByIterator(wishlistRepository.findAllByUser(getCurrentUser()), WishlistResponse.class);
    }

    public User getCurrentUser() {
       User user =  userService.getCurrentUser();
       if(user == null) throw new RuntimeException("User not found");
       return user;
    }

    public List<Wishlist> convertRequestToEntity(User user, List<WishlistRequest> request) {
        WishlistBuilder builder = Wishlist.builder();
        List<Wishlist> list = request.stream().map(w -> {
          return  builder.withId(0)
            .withUser(user)
            .withProduct(getProductById(w.getProduct_id())).build();
        }).collect(Collectors.toCollection(ArrayList::new));

        return list;
    }

    public Product getProductById(Integer productId) {
      return productRepository.findById(productId).orElseThrow(() -> new EntityNotFoundException("Product id " + productId + " not found "));


    }
    
}
