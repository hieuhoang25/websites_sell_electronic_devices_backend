package com.poly.datn.repository;

import com.poly.datn.entity.Rating;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RatingRepository extends JpaRepository<Rating, Integer> {
    List<Rating> findByProductId(Integer productId);

    @Query("select case " +
            "when o.orderDetail.id =:orderDetailId " +
            "and o.product.id =:productId " +
            "then true else false end " +
            " from Rating o where o.user.id =:userId")
    Boolean isRating(Integer productId, Integer userId, Integer orderDetailId);

}