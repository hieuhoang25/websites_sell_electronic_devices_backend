package com.poly.datn.service.serviceImpl;

import com.poly.datn.common.mapper.ModelConverter;
import com.poly.datn.dto.request.OrderStatusCreateRequest;
import com.poly.datn.dto.request.OrderStatusUpdateRequest;
import com.poly.datn.dto.response.OrderStatusResponse;
import com.poly.datn.entity.OrderStatus;
import com.poly.datn.repository.OrderStatusRepository;
import com.poly.datn.service.CRUDOrderStatusService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(rollbackFor = Exception.class)
public class CRUDOrderStatusServiceImpl implements CRUDOrderStatusService {
    private final OrderStatusRepository repository;
    private final ModelConverter converter;

    @Override
    public List<OrderStatusResponse> getOrderStatus() {
        List<OrderStatus> orderStatus = repository.findAll();
        List<OrderStatusResponse> orderStatusResponse = converter.mapAllByIterator(orderStatus, OrderStatusResponse.class);
        if (orderStatus.isEmpty()) {
            OrderStatusResponse statusResponse = new OrderStatusResponse();
            statusResponse.setId(-1);
            statusResponse.setName("Empty record");
            orderStatusResponse.add(statusResponse);
        }
        return orderStatusResponse;
    }

    @Override
    public OrderStatusResponse create(OrderStatusCreateRequest request) {
        OrderStatus orderStatus = converter.map(request, OrderStatus.class);
        OrderStatusResponse orderStatusResponse = converter.map(repository.save(orderStatus), OrderStatusResponse.class);
        return orderStatusResponse;
    }

    @Override
    public OrderStatusResponse update(OrderStatusUpdateRequest request) {
        OrderStatus orderStatus = converter.map(request, OrderStatus.class);
        OrderStatusResponse orderStatusResponse = converter.map(repository.save(orderStatus), OrderStatusResponse.class);
        return orderStatusResponse;
    }

    @Override
    public void delete(Integer id) {
        repository.deleteById(id);
    }
}
