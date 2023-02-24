package com.poly.datn.repository;

import com.poly.datn.entity.ProductAttribute;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.List;

public interface ProductAttributeRepository extends JpaRepository<ProductAttribute, Integer> {
    List<ProductAttribute> findByProductId(Integer id);


}