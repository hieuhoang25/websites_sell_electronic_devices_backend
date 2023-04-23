package com.poly.datn.repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.poly.datn.entity.CartDetail;

public interface CartDetailRepository extends JpaRepository<CartDetail, Integer>, CartDetailCustomRepository{
   
    CartDetail findByProductVariantId(Integer variantId);

    @Query("select d from CartDetail d where d.cart.id =:cartId and d.id=:itemId")
    Optional<CartDetail> findByCartId(@Param("cartId") Integer cartId, @Param("itemId") Integer itemId);

   Optional<List<CartDetail>> findAllByCartId(Integer cartId);

   Optional<List<CartDetail>> findAllByCartIdOrderByCreateDateDesc(Integer cartId);

   @Query(value = "select quantity from cart_detail where cart_id =:cartId and product_variant_id =:variantId", nativeQuery = true)
   Optional<Integer> countVariantQuantityInCart(@Param("cartId") Integer cartId, @Param("variantId") Integer variantId );


   @Modifying(flushAutomatically = true, clearAutomatically = true)
   @Query(value = "delete from cart_detail where cart_id =:cartId and quantity > 0", nativeQuery = true)
   Integer deleteAllByCartId(@Param("cartId") Integer cartId);

   @Modifying(flushAutomatically = true, clearAutomatically = true)
   @Query(value = "delete from cart_detail where cart_id =:cartId and id in :ids", nativeQuery = true)
   Integer deleteAllWithIdIn(@Param("cartId") Integer cartId, @Param("ids") List<Integer> ids);

   @Modifying(flushAutomatically = true)
   @Query(value = "delete from cart_detail where id=:detailId", nativeQuery = true)
   void deleteById(@Param("detailId") Integer detailId);

   @Query("select d from  CartDetail d where d.cart.id =:cartId and d.productVariant.id=:variantId")
   CartDetail findByProductVariantId(Integer cartId, Integer variantId);
}