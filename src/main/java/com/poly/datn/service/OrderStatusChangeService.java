package com.poly.datn.service;

import com.poly.datn.dto.request.OderStatusChangeRequest;

public interface OrderStatusChangeService {

    void changeOrderStatus(OderStatusChangeRequest request);

}
