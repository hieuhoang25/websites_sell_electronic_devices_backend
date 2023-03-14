package com.poly.datn.service;

import com.poly.datn.dto.response.ColorResponse;

import java.util.List;

public interface CURDColorService {
    List<ColorResponse> findAll();
}
