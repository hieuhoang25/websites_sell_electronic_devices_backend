package com.poly.datn.service;

import com.poly.datn.dto.response.ColorResponse;
import com.poly.datn.dto.response.StorageResponse;

import java.util.List;

public interface CURDStorageService {
    List<StorageResponse> findAll();

}
