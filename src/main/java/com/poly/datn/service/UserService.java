package com.poly.datn.service;

import com.poly.datn.dto.response.UserResponse;

import java.util.List;


public interface UserService {
    List<UserResponse> findAll();

}
