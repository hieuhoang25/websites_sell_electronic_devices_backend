package com.poly.datn.repository;

import com.poly.datn.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Integer> {
    @Query("select o from Order o where o.status.id=:idStatus")
    List<Order> findByStatus(Integer idStatus);
}