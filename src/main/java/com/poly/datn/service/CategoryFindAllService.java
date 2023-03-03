package com.poly.datn.service;

import com.poly.datn.dto.response.CategoryFindAllResponse;

import java.util.List;

public interface CategoryFindAllService {
    List<CategoryFindAllResponse> getCategory();
}
