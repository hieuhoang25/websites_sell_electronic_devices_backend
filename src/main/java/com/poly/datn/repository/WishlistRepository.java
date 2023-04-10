package com.poly.datn.repository;

import java.util.List;
import java.util.Set;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.poly.datn.entity.User;
import com.poly.datn.entity.Wishlist;

public interface WishlistRepository extends JpaRepository<Wishlist, Integer> {

    List<Wishlist> findAllByUser(User user);
    Page<Wishlist> findAllByUserOrderByUpdateDateDesc(User user,Pageable page);

    @Modifying(flushAutomatically = true)
    @Query("delete from Wishlist w where  w.user =:user and w.product.id in :productIds ")
    Integer deleteAllByProductIds(@Param("productIds") List<Integer> productIds, @Param("user") User user);
    @Query("select case " +
            " when o.product.id=:productId" +
            " then true else false end" +
            " from Wishlist o where o.user.id=:userId and o.product=:productId")
    Boolean findByProductIdAndUserId(Integer productId, Integer userId);
}