package com.poly.datn.service.serviceImpl;

import com.poly.datn.common.mapper.ModelConverter;
import com.poly.datn.dto.response.OrderTrackingResponse;
import com.poly.datn.dto.response.PaginationOrderTracking;
import com.poly.datn.entity.Order;
import com.poly.datn.repository.OrderRepository;
import com.poly.datn.repository.specification.OrderSpecification;
import com.poly.datn.service.OrderTrackingService;
import com.poly.datn.service.UserInfoByTokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(rollbackFor = {Exception.class})
public class OrderTrackingServiceImpl implements OrderTrackingService {
    private final OrderRepository repository;
    private final ModelConverter converter;
    private final UserInfoByTokenService userInfoByTokenService;

    @Override
    public PaginationOrderTracking<OrderTrackingResponse> getUserOrdered(Integer statusId, Pageable pageable) {
        OrderSpecification orderSpecification = new OrderSpecification();
        Integer currentUser = userInfoByTokenService.getCurrentUser().getId();
        Specification specification = orderSpecification.getUserOrdered(statusId,currentUser);
        Page<OrderTrackingResponse> orders = repository.findAll(specification,pageable);
        return new PaginationOrderTracking<>(
                orders.getSize(),
                orders.getNumber(),
                orders.getTotalPages(),
                orders.getTotalElements(),
                converter.mapAllByIterator(orders.getContent(),OrderTrackingResponse.class)

        );
    }
}
