package com.poly.datn.service.serviceImpl;

import com.poly.datn.common.mapper.ModelConverter;
import com.poly.datn.dto.response.OrderDetailResponse;
import com.poly.datn.entity.OrderDetail;
import com.poly.datn.repository.OrderDetailRepository;
import com.poly.datn.service.OrderDetailService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(rollbackFor = {Exception.class})
public class OrderDetailServiceImpl implements OrderDetailService {
    private final OrderDetailRepository repository;
    private final ModelConverter converter;

    @Override
    public List<OrderDetailResponse> getOrderDetail(Integer idOrder) {
        List<OrderDetail> orderDetail = repository.findByOrderId(idOrder);
        return converter.mapAllByIterator(orderDetail,OrderDetailResponse.class);
    }
}
