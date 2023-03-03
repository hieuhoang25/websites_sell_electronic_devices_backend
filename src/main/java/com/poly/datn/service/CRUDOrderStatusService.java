package com.poly.datn.service;

import com.poly.datn.dto.request.OrderStatusCreateRequest;
import com.poly.datn.dto.request.OrderStatusUpdateRequest;
import com.poly.datn.dto.response.OrderStatusResponse;

import java.util.List;

public interface CRUDOrderStatusService {
    List<OrderStatusResponse> getOrderStatus();
    OrderStatusResponse create(OrderStatusCreateRequest request);
    OrderStatusResponse update(OrderStatusUpdateRequest request);
    void delete(Integer id);
}
