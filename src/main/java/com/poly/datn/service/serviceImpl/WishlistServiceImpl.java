package com.poly.datn.service.serviceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.poly.datn.common.SearchResult;
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
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional(rollbackOn = Exception.class)
public class WishlistServiceImpl implements WishlistService {

  final ModelConverter converter;
  final WishlistRepository wishlistRepository;
  final UserInfoByTokenService userService;
  final ProductRepository productRepository;

  @Override
  public List<WishlistResponse> addProductToWishlist(List<WishlistRequest> request) {
    log.info("calling addProductToWishlist()");

    List<Wishlist> wishlists = convertRequestToEntity(getCurrentUser(), request);

    log.info("get list and return...");
    try {
      wishlistRepository.saveAllAndFlush(wishlists);
    } catch (Exception ex) {
      ex.printStackTrace();
      if (ex instanceof DataIntegrityViolationException) {
        log.info("duplicated");
        throw new RuntimeException("Product's already in user's wishlists");
      }
    }

    return getWishlistOfCurrentUser();
  }

  @Override
  public List<WishlistResponse> deleteProductFromWishlist(List<WishlistRequest> requests) {
    List<Integer> listIds = requests.stream().map(r -> r.getProduct_id()).collect(Collectors.toList());
    Integer deletedCount = wishlistRepository.deleteAllByProductIds(listIds, getCurrentUser());
    log.info("finished deleted " + deletedCount + " item(s).");
    if (deletedCount == 0)
      throw new EntityNotFoundException("Product not found ");

    return getWishlistOfCurrentUser();
  }

  @Override
  public List<WishlistResponse> getWishlistOfCurrentUser() {
    try {
      return converter.mapAllByIterator(wishlistRepository.findAllByUser(getCurrentUser()), WishlistResponse.class);
      // return
      // mappingIterator(List.copyOf(wishlistRepository.findAllByUser(getCurrentUser())));
    } catch (Exception ex) {
      ex.printStackTrace();
      return null;
    }
  }

  @Override
  public Boolean findWishlistOfUserByProductId(Integer productId) {
    Boolean isExists = wishlistRepository.findByProductIdAndUserId(productId,userService.getCurrentUser().getId());
    if(isExists == null)
      return false;
    return isExists;
  }

  public User getCurrentUser() {
    User user = userService.getCurrentUser();
    if (user == null)
      throw new RuntimeException("User not found");
    return user;
  }

  public List<Wishlist> convertRequestToEntity(User user, List<WishlistRequest> request) {
    log.info(" start to convert in convertRequestToEntity()");
    WishlistBuilder builder = Wishlist.builder();
    log.info("builder = " + builder);
    List<Wishlist> list = request.stream().map(w -> {
      try {

        Wishlist wl = builder.withId(0).withUser(user).withProduct(getProductById(w.getProduct_id())).build();
        return wl;
      } catch (Exception e) {
        log.info("convert failed...");
        e.printStackTrace();
        throw new RuntimeException(((EntityNotFoundException) e).getMessage());
      }
    }).collect(Collectors.toCollection(ArrayList::new));
    log.info("finished mapping request to entity");
    return list;
  }

  public Product getProductById(Integer productId) {
    return productRepository.findById(productId)
        .orElseThrow(() -> new EntityNotFoundException("Product id " + productId + " not found "));

  }

  @Override
  public SearchResult<WishlistResponse> getWishlistOfCurrentUser(Pageable page) {
    Page<Wishlist> wishlists =  wishlistRepository.findAllByUserOrderByUpdateDateDesc(getCurrentUser(),page);
    return new SearchResult(wishlists.getSize(),
    wishlists.getNumber(),wishlists.getTotalPages(),
converter.mapAllByIterator(wishlists.getContent(), WishlistResponse.class));
  }
}
