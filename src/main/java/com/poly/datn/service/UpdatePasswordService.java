package com.poly.datn.service;

import com.poly.datn.dto.request.UpdatePasswordRequest;
import com.poly.datn.dto.response.JwtResponse;
import org.springframework.http.ResponseEntity;

public interface UpdatePasswordService {
    ResponseEntity<?> updatePassword(UpdatePasswordRequest request);
}
