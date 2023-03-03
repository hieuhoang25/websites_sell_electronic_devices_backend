package com.poly.datn.service;

import com.poly.datn.dto.response.OrderManagerResponse;

import java.util.List;

public interface OrderFindByStatusService {
    List<OrderManagerResponse> getOrder(Integer idStatus);
}
