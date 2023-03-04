package com.poly.datn.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.poly.datn.dto.response.CartResponse;
import com.poly.datn.entity.Cart;

public interface CartRepository extends JpaRepository<Cart, Integer> {

    @Query("select c from Cart c where c.user.id =:userId")
    Cart findCartByUserId(@Param("userId") Integer userId);



   
}