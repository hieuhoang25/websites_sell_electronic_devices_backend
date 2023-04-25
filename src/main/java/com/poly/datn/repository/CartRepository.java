package com.poly.datn.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;

import com.poly.datn.entity.Cart;

public interface CartRepository extends JpaRepository<Cart, Integer>,CartCustomRespository {

    @Query("select c from Cart c where c.user.id =:userId")
    Optional<Cart> findCartByUserId(@Param("userId") Integer userId);


    boolean existsById(Integer id);

    @Modifying(flushAutomatically = true,clearAutomatically = true)
    @Procedure(procedureName = "sp_sumTotalInCart")
    Integer updateCartPriceSum(Integer cartId );

    @Modifying(flushAutomatically = true,clearAutomatically = true)
    @Procedure(procedureName = "sp_updateCartByInventory")
    Boolean updatedCartQuantityByInventory(Integer cartId );


    @Modifying(flushAutomatically = true,clearAutomatically = true)
    @Procedure(procedureName = "sp_updateCartByVariantStatus")
    Boolean updatedCartByVariantStatus(Integer cartId );
}
