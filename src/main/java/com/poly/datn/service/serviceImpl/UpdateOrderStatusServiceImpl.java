package com.poly.datn.service.serviceImpl;

import com.poly.datn.common.mapper.ModelConverter;
import com.poly.datn.dto.response.OrderManagerResponse;
import com.poly.datn.entity.Order;
import com.poly.datn.repository.OrderRepository;
import com.poly.datn.service.UpdateOrderStatusService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(rollbackFor = {Exception.class})
public class UpdateOrderStatusServiceImpl implements UpdateOrderStatusService {
    private final OrderRepository repository;
    private final ModelConverter converter;

    @Override
    public OrderManagerResponse updateStatusToReceive(Integer idOrder) {
        Order order = repository.updateStatusToReceive(idOrder);
        OrderManagerResponse orderManagerResponse = converter.map(order, OrderManagerResponse.class);
        return orderManagerResponse;
    }

    @Override
    public OrderManagerResponse updateStatusToCompleted(Integer idOrder) {
        Order order = repository.updateStatusToCompleted(idOrder);
        OrderManagerResponse orderManagerResponse = converter.map(order, OrderManagerResponse.class);
        return orderManagerResponse;
    }
}
