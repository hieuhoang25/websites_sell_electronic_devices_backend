package com.poly.datn.repository;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;

import com.poly.datn.entity.User;
import com.poly.datn.entity.Wishlist;

public interface WishlistRepository extends JpaRepository<Wishlist, Integer> {


    
    Set<Wishlist> findAllByUser(User user);
}