package com.poly.datn.repository;

import com.poly.datn.entity.Color;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ColorRepository extends JpaRepository<Color, Integer> {
    @Query("select distinct o.color from ProductVariant o where o.product.id=:productId and o.status=true")
    List<Color> findByProduct(Integer productId);

}