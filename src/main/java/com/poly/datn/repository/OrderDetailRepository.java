package com.poly.datn.repository;

import com.poly.datn.entity.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface OrderDetailRepository extends JpaRepository<OrderDetail, Integer> {
    List<OrderDetail> findByOrderId(Integer idOrder);

    @Query(value = "select sum(d.priceSum*d.quantity - d.promotionValue) from OrderDetail  d where d.createDate >= ?1 and d.createDate < ?2")
    Double revenue(LocalDateTime lastWeek, LocalDateTime thisWeek);


    @Query(value = "select count(d.quantity) from OrderDetail d")
    Integer countSelled();

    @Query(value = "SELECT SUM(price_sum - promotion_value) " +
            "FROM order_detail " +
            "WHERE create_date BETWEEN DATE_SUB(CURDATE(), INTERVAL 1 MONTH) AND LAST_DAY(DATE_SUB(CURDATE(), INTERVAL 1 MONTH)) and DAYOFWEEK(create_date) = :day " +
            "GROUP BY DAYOFWEEK(create_date)",nativeQuery = true)
    Double envennuByWeekdayOfLastMonth(@Param("day") Integer day);

    @Query(value = "SELECT SUM(price_sum - promotion_value) " +
            "FROM order_detail " +
            "WHERE MONTH(create_date) = MONTH(CURDATE()) AND YEAR(create_date) = YEAR(CURDATE()) and DAYOFWEEK(create_date) = :day " +
            "GROUP BY DAYOFWEEK(create_date)", nativeQuery = true)
    Double envennuByWeekdayOfThisMonth(@Param("day") Integer day);
}