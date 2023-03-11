package com.poly.datn.service;

import org.springframework.stereotype.Service;

import com.poly.datn.dto.response.OrderResponse;


public interface OrderTrackingByIdService {

    OrderResponse findByOrderId(Integer id);
    
}
