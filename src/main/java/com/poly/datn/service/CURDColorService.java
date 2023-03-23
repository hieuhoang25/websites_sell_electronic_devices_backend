package com.poly.datn.service;

import com.poly.datn.dto.response.ColorResponse;
import com.poly.datn.dto.response.Pagination;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CURDColorService {
    List<ColorResponse> findAll();


}
