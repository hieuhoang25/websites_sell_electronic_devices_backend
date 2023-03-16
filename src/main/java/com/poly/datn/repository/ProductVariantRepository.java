package com.poly.datn.repository;

import java.util.List;

import com.poly.datn.dto.response.ProductSellingTop;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.poly.datn.entity.ProductVariant;

import javax.transaction.Transactional;

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

    @Query("select v.status from ProductVariant v where v.id =:id ")    
    boolean isStatusTrue(@Param("id") Integer id);
    @Query(value = "select product_id, p.product_name , sum(od.price_sum*od.quantity - od.promotion_value) as revenue , sum(pv.quantity) as stock from product_variant pv " +
            "inner join order_detail od on pv.id = od.product_variant_id " +
            "inner join product p on pv.product_id = p.id " +
            "group by product_id",nativeQuery = true)
    List<ProductSellingTop>  productSellingTops();

    @Modifying
    @Transactional
    @Query(value = "UPDATE product_variant SET status = :isDeleted WHERE id = :id LIMIT 1",nativeQuery = true)
    void delete(@Param("id") Integer id,@Param("isDeleted") Integer isDeleted);
}