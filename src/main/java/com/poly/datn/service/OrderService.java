package com.poly.datn.service;


import com.poly.datn.dto.response.OrdersUserResponse;

import java.util.List;
import java.util.Map;

public interface OrderService {

    List<OrdersUserResponse> findAll();
    List<OrdersUserResponse> findAll(List<String> status);
    Map<String, Object> countOrdersByStatus();

}
