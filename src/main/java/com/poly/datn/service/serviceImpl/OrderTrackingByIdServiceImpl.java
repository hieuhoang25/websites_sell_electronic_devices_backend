package com.poly.datn.service.serviceImpl;

import org.springframework.stereotype.Service;

import com.poly.datn.common.mapper.ModelConverter;
import com.poly.datn.dto.response.OrderResponse;
import com.poly.datn.repository.OrderRepository;
import com.poly.datn.service.OrderTrackingByIdService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrderTrackingByIdServiceImpl implements OrderTrackingByIdService{

    final OrderRepository orderRepository;
    final ModelConverter modelConverter;
    @Override
    public OrderResponse findByOrderId(Integer orderId) {
      return modelConverter.map(orderRepository.findById(orderId).orElseThrow(()-> new RuntimeException("Order not found")), OrderResponse.class) ;
    }
    
}
