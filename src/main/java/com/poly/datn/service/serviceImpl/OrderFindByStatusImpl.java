package com.poly.datn.service.serviceImpl;

import com.poly.datn.common.mapper.ModelConverter;
import com.poly.datn.dto.response.OrderResponse;
import com.poly.datn.entity.Order;
import com.poly.datn.repository.OrderRepository;
import com.poly.datn.service.OrderFindByStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor
public class OrderFindByStatusImpl implements OrderFindByStatus {
    private final OrderRepository repository;
    private final ModelConverter converter;
    @Override
    public List<OrderResponse> getOrder(Integer idStatus) {
        List<Order> order = repository.findByStatus(idStatus);
        List<OrderResponse> orderResponses = converter.mapAllByIterator(order,OrderResponse.class);
        return orderResponses;
    }
}
