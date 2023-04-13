package com.poly.datn.service;

import com.poly.datn.dto.response.OrderManagerResponse;

public interface UpdateOrderStatusService {
    void updateStatusToReceive(Integer idOrder);
    void updateStatusToCompleted(Integer idOrder);

    void updateStatusToCancelled(Integer idOrder);
}
