package com.poly.datn.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.poly.datn.entity.CartDetail;

public interface CartDetailRepository extends JpaRepository<CartDetail, Integer> {
   
    CartDetail findByProductVariantId(Integer variantId);
}