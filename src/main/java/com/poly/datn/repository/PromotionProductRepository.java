package com.poly.datn.repository;

import java.time.Instant;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.poly.datn.entity.PromotionProduct;

public interface PromotionProductRepository extends JpaRepository<PromotionProduct, Integer> {

    // updatedDate = start date
    @Query(value = "select * from promotion_product  where activate =  and ( :start <= updated_date or updated_date is null) and (expiration_date < :end)",  nativeQuery = true )
   List<PromotionProduct> findAllInDayIncludedInActive(@Param("start") String start, @Param("end") String end);   

//    @Query(value = "select * from promotion_product  where activate =  and ( :start <= updated_date or updated_date is null) and (expiration_date < :end)",  nativeQuery = true )
   List<PromotionProduct>  findByUpdatedDateBetweenAndActivate(Instant from, Instant to, boolean activate); 

   List<PromotionProduct>  findByUpdatedDateBetweenOrderByUpdatedDateAsc(Instant from, Instant to); 

   List<PromotionProduct>  findByUpdatedDateBetweenAndActivateOrderByUpdatedDateAsc(Instant from, Instant to, boolean activate); 
    @Query("select p from PromotionProduct p " +
            "where p.expirationDate is not null " +
            "and p.updatedDate is not null " +
            "and p.expirationDate >:now " +
            "and p.activate = true " +
            "and p.id in (SELECT pr.promotion.id from Product pr)" +
            "order by p.expirationDate asc")
   List<PromotionProduct> findAllValidPromotion(Instant now);
}