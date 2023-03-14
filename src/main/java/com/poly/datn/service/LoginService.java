package com.poly.datn.service;

import com.poly.datn.dto.request.LoginRequest;

import java.util.Map;

public interface LoginService {
    Map<String,String> login(LoginRequest request);
}
