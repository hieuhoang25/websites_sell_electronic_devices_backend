package com.poly.datn.service;

import com.poly.datn.dto.request.OrderDetailRequest;

import java.util.List;

public interface CancelOrderService {
    void cancelOrder(Integer idOrder, List<OrderDetailRequest> request);
}
