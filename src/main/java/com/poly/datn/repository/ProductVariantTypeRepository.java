package com.poly.datn.repository;

import com.poly.datn.entity.ProductVariantType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductVariantTypeRepository extends JpaRepository<ProductVariantType, Integer> {
}