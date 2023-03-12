package com.poly.datn.repository;

import com.poly.datn.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Integer> {

    List<Order> findByStatusId(Integer idStatus);
    @Modifying(flushAutomatically = true)
    @Query("update Order o set o.status.id=2 where o.id=:idOrder")
    Order updateStatusToReceive(Integer idOrder);

    @Modifying(flushAutomatically = true)
    @Query("update Order o set o.status.id=3 where o.id=:idOrder")
    Order updateStatusToCompleted(Integer idOrder);

    List<Order> findByUserId(Integer userId);
    List<Order> findByUserIdAndStatusId(Integer userId, Integer statusId);

    @Query(value = "select count(o) from Order o where o.status.id = 1")
    Integer countOrderYetApprove();


}