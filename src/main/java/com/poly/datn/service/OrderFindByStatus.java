package com.poly.datn.service;

import com.poly.datn.dto.response.OrderResponse;

import java.util.List;

public interface OrderFindByStatus {
    List<OrderResponse> getOrder(Integer idStatus);
}
