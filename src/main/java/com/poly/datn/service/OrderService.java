package com.poly.datn.service;


import com.poly.datn.dto.response.OrdersUserResponse;

import java.util.List;

public interface OrderService {

    List<OrdersUserResponse> findAll();

}
