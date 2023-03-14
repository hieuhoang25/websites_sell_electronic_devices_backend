package com.poly.datn.repository;

import com.poly.datn.entity.Wishlist;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WishlistRepository extends JpaRepository<Wishlist, Integer> {
}