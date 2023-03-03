package com.poly.datn.service;

import com.poly.datn.dto.response.OrderTrackingResponse;

import java.util.List;

public interface OrderFindByUserService {
    List<OrderTrackingResponse> getUserOrdered(Integer userId);
}
