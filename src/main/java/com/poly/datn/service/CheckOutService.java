package com.poly.datn.service;

import com.poly.datn.dto.request.CheckOutRequest;


public interface CheckOutService {

    Integer checkout(Integer userId, CheckOutRequest request);

}
