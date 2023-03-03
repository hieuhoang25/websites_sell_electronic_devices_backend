package com.poly.datn.service;

import com.poly.datn.dto.response.OrderDetailResponse;

import java.util.List;

public interface OrderDetailService {
    List<OrderDetailResponse> getOrderDetail(Integer idOrder);
}
