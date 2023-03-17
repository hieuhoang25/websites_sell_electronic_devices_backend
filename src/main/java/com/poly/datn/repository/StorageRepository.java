package com.poly.datn.repository;

import com.poly.datn.entity.Storage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface StorageRepository extends JpaRepository<Storage, Integer> {
    @Query("select distinct o.storage from ProductVariant o " +
            "where o.color.id=:colorId and o.product.id=:productId and o.status=true")
    List<Storage> findByColorAndProduct(Integer colorId, Integer productId);
}