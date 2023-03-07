package com.poly.datn.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.poly.datn.entity.CartDetail;

public interface CartDetailRepository extends JpaRepository<CartDetail, Integer> {
   
    CartDetail findByProductVariantId(Integer variantId);

    @Query("select d from CartDetail d where d.cart.id =:cartId and d.id=:itemId")
    Optional<CartDetail> findByCartId(@Param("cartId") Integer cartId, @Param("itemId") Integer itemId);

   Optional<List<CartDetail>> findAllByCartId(Integer cartId);

   @Query(value = "select quantity from cart_detail where cart_id =:cartId and product_variant_id =:variantId", nativeQuery = true)
   Optional<Integer> countVariantQuantityInCart(@Param("cartId") Integer cartId, @Param("variantId") Integer variantId );


   @Modifying(flushAutomatically = true)
   @Query(value = "delete from cart_detail where cart_id =:cartId", nativeQuery = true)
   Integer deleteAllByCartId(@Param("cartId") Integer cartId);
}