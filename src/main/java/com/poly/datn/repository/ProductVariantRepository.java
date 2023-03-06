package com.poly.datn.repository;


import com.poly.datn.entity.ProductVariant;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;



public interface ProductVariantRepository extends JpaRepository<ProductVariant, Integer> {
    @Query("select o from ProductVariant o where o.product.id=:productId " +
            "and o.color.id=:colorId and o.status=true and o.storage.id=:storageId")
    ProductVariant findByProductAndColor(Integer colorId, Integer productId, Integer storageId);



    @Query(value = "select * from product_variant pv left join color c " +
            "on pv.color_id = c.id left join storage s on " +
            "s.id = pv.storage_id where product_id = :id",nativeQuery = true)
    List<ProductVariant> findByProductId(Integer id, Pageable pageable);

    @Query(value = "select * from product_variant pv left join color c " +
            "on pv.color_id = c.id left join storage s on " +
            "s.id = pv.storage_id where product_id = :id",nativeQuery = true)
    List<ProductVariant> findByProduct(Integer id);


}