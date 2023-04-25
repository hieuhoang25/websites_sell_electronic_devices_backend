package com.poly.datn.repository;

import com.poly.datn.entity.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Integer>, JpaSpecificationExecutor {

    List<Order> findByStatusId(Integer idStatus);
    @Modifying(flushAutomatically = true)
    @Query("update Order o set o.status.id=2 where o.id=:idOrder")
    void updateStatusToReceive(Integer idOrder);

    @Modifying(flushAutomatically = true)
    @Query("update Order o set o.status.id=3,o.isPay = true where o.id=:idOrder")
    void updateStatusToCompleted(Integer idOrder);

    @Query(value = "select count(o) from Order o where o.status.id = 1")
    Integer countOrderYetApprove();

    @Query(value = "select count(o) from Order o where o.status.id = ?1")
    Integer countNumberOfOrderByStatus(Integer id);

    @Modifying(flushAutomatically = true)
    @Query("update Order o set o.status.id=4,o.isPay = false where o.id=:idOrder")
    void updateStatusToCancelled(Integer idOrder);
}