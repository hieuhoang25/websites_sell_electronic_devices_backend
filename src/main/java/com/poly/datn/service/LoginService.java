package com.poly.datn.service;

import com.poly.datn.dto.request.LoginRequest;
import org.springframework.http.ResponseEntity;

import java.util.Map;

public interface LoginService {
    ResponseEntity<?> login(LoginRequest request);
}
