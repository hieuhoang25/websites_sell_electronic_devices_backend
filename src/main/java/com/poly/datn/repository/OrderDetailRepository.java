package com.poly.datn.repository;

import com.poly.datn.entity.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;

public interface OrderDetailRepository extends JpaRepository<OrderDetail, Integer> {
    List<OrderDetail> findByOrderId(Integer idOrder);

    @Query(value = "select sum(d.priceSum*d.quantity - d.promotionValue) from OrderDetail  d where d.createDate >= ?1 and d.createDate < ?2")
    Double revenue(LocalDateTime lastWeek, LocalDateTime thisWeek);


    @Query(value = "select count(d.quantity) from OrderDetail d")
    Integer countSelled();

    @Query(value = "SELECT sum(o.priceSum*o.quantity - o.promotionValue) FROM OrderDetail o WHERE FUNCTION('DAY_OF_WEEK', o.createDate) = ?1 AND o.createDate >= FUNCTION('DATE_SUB', CURRENT_DATE(),7)")
    Double envennuByWeekdayOfLastWeek(Integer day);

    @Query(value = "SELECT sum(o.priceSum*o.quantity - o.promotionValue) FROM OrderDetail o WHERE FUNCTION('DAY_OF_WEEK', o.createDate) = ?1 AND o.createDate >= FUNCTION('DATE', FUNCTION('NOW'))")
    Double envennuByWeekdayOfThisWeek(Integer day);
}