package com.poly.datn.service;

import com.poly.datn.dto.request.LoginRequest;
import org.springframework.http.ResponseEntity;


public interface LoginService {
    ResponseEntity<?> login(LoginRequest request);
}
