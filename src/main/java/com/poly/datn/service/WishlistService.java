package com.poly.datn.service;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.poly.datn.common.SearchResult;
import com.poly.datn.dto.request.WishlistRequest;
import com.poly.datn.dto.response.WishlistResponse;

public interface WishlistService {

    // Get userId from current logged-in user

    public List<WishlistResponse> addProductToWishlist(List<WishlistRequest> productId);

    public List<WishlistResponse> deleteProductFromWishlist(List<WishlistRequest> productId);

    public List<WishlistResponse> getWishlistOfCurrentUser();
    public SearchResult<WishlistResponse> getWishlistOfCurrentUser(Pageable page);

    Boolean findWishlistOfUserByProductId(Integer productId);
}
