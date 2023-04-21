package com.poly.datn.repository;

import com.poly.datn.entity.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface OrderDetailRepository extends JpaRepository<OrderDetail, Integer> {
    List<OrderDetail> findByOrderId(Integer idOrder);

    @Query(value = "select sum(d.priceSum - d.promotionValue*d.quantity) from OrderDetail d where d.order.isPay=true")
    Double revenue();


    @Query(value = "select count(d.quantity) from OrderDetail d")
    Integer countSelled();

    @Query(value = "SELECT SUM(order_detail.price_sum - order_detail.promotion_value*order_detail.quantity) " +
            "          FROM order_detail " +
            "          inner join orders  on orders.id = order_detail.order_id " +
            "          WHERE orders.is_pay = TRUE and  order_detail.create_date BETWEEN DATE_SUB(CURDATE(), INTERVAL 1 MONTH) AND LAST_DAY(DATE_SUB(CURDATE(), INTERVAL 1 MONTH)) and DAYOFWEEK(order_detail.create_date) = :day " +
            "          GROUP BY DAYOFWEEK(order_detail.create_date)",nativeQuery = true)
    Double envennuByWeekdayOfLastMonth(@Param("day") Integer day);

    @Query(value = "SELECT SUM(order_detail.price_sum - order_detail.promotion_value*order_detail.quantity) " +
            "            FROM order_detail " +
            "            inner join orders  on orders.id = order_detail.order_id " +
            "            WHERE orders.is_pay = TRUE and  MONTH(order_detail.create_date) = MONTH(CURDATE()) AND YEAR(order_detail.create_date) = YEAR(CURDATE()) and DAYOFWEEK(order_detail.create_date) = :day " +
            "            GROUP BY DAYOFWEEK(order_detail.create_date)", nativeQuery = true)
    Double envennuByWeekdayOfThisMonth(@Param("day") Integer day);

}