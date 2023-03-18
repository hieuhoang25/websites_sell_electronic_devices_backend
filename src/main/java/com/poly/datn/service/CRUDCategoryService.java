package com.poly.datn.service;

import com.poly.datn.dto.request.CategoryRequest;
import com.poly.datn.dto.response.CategoryResponse;

import java.util.List;

/*
* CUD: CREATE, UPDATE, DELETE FOR CATEGORY
* */
public interface CRUDCategoryService {

    List<CategoryResponse> findAll();
    List<CategoryResponse> findAllParentAndChild();

    CategoryResponse findById(Integer id);
    CategoryResponse create(CategoryRequest categoryRequest);

    CategoryResponse update(CategoryRequest categoryRequest);

    void delete(Integer id);

}
