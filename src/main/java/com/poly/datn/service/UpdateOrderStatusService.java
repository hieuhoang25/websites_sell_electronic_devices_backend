package com.poly.datn.service;

import com.poly.datn.dto.response.OrderManagerResponse;

public interface UpdateOrderStatusService {
    OrderManagerResponse updateStatusToReceive(Integer idOrder);
    OrderManagerResponse updateStatusToCompleted(Integer idOrder);
}
