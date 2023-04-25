package com.poly.datn.service;

import java.util.List;

import com.poly.datn.dto.request.CheckOutRequest;
import com.poly.datn.dto.response.PaymentMethodResponse;
import com.poly.datn.exception.InventoryException;


public interface CheckOutService {

    Integer checkout(Integer userId, CheckOutRequest request) throws InventoryException;

    List<PaymentMethodResponse> getPaymentMethod();

}
