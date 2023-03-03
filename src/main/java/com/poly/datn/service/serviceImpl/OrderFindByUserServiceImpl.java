package com.poly.datn.service.serviceImpl;

import com.poly.datn.common.mapper.ModelConverter;
import com.poly.datn.dto.response.OrderTrackingResponse;
import com.poly.datn.entity.Order;
import com.poly.datn.repository.OrderRepository;
import com.poly.datn.service.OrderFindByUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(rollbackFor = {Exception.class})
public class OrderFindByUserServiceImpl implements OrderFindByUserService {
    private final OrderRepository repository;
    private final ModelConverter converter;
    @Override
    public List<OrderTrackingResponse> getUserOrdered(Integer userId) {
        List<Order> orders = repository.findByUserId(userId);
        List<OrderTrackingResponse> orderTrackingResponses = converter.mapAllByIterator(orders,OrderTrackingResponse.class);
        return orderTrackingResponses;
    }
}
