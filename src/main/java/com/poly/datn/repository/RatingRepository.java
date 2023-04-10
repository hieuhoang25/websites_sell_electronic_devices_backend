package com.poly.datn.repository;

import com.poly.datn.entity.OrderDetail;
import com.poly.datn.entity.Rating;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RatingRepository extends JpaRepository<Rating, Integer> {
    List<Rating> findByProductId(Integer productId);

    @Query("select o from OrderDetail o " +
            "where o.id not in (select r.orderDetail.id from Rating r) " +
            "and o.order.id=:orderId " +
            "and o.order.user.id=:userId")
    List<OrderDetail> isRating(Integer orderId, Integer userId);
}