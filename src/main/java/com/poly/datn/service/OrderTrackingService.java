package com.poly.datn.service;

import com.poly.datn.dto.response.OrderTrackingResponse;
import com.poly.datn.dto.response.PaginationOrderTracking;
import org.springframework.data.domain.Pageable;

public interface OrderTrackingService {
    PaginationOrderTracking<OrderTrackingResponse> getUserOrdered(Integer statusId, Pageable pageable);
}
