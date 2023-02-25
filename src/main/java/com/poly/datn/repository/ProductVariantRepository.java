package com.poly.datn.repository;


import com.poly.datn.entity.ProductVariant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;



public interface ProductVariantRepository extends JpaRepository<ProductVariant, Integer> {
    @Query("select o from ProductVariant o where o.product.id=:productId " +
            "and o.color.id=:colorId and o.status=true and o.storage.id=:storageId")
    ProductVariant findByProductAndColor(Integer colorId, Integer productId, Integer storageId);



//    @Query(value = "select * from product_variant where product_id = :id",nativeQuery = true)
    List<ProductVariant> findByProductId(Integer id);


}