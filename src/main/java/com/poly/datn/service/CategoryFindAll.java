package com.poly.datn.service;

import com.poly.datn.dto.response.CategoryFindAllResponse;
import com.poly.datn.entity.Category;

import java.util.List;

public interface CategoryFindAll {
    List<CategoryFindAllResponse> getCategory();
}
