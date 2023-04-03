package com.poly.datn.service.serviceImpl;

import com.poly.datn.common.mapper.ModelConverter;
import com.poly.datn.dto.response.OrderManagerResponse;
import com.poly.datn.entity.Order;
import com.poly.datn.repository.OrderRepository;
import com.poly.datn.service.OrderFindByStatusService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
@RequiredArgsConstructor
@Transactional(rollbackFor = {Exception.class})
public class OrderFindByStatusServiceImpl implements OrderFindByStatusService {
    private final OrderRepository repository;
    private final ModelConverter converter;
    @Override
    public List<OrderManagerResponse> getOrder(Integer idStatus) {
        List<Order> order = repository.findByStatusId(idStatus);
        List<OrderManagerResponse> orderManagerResponse = converter.mapAllByIterator(order, OrderManagerResponse.class);
        return orderManagerResponse;
    }
}
