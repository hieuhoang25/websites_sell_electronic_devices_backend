package com.poly.datn.service;

import com.poly.datn.common.SearchResult;
import com.poly.datn.dto.request.ProductRequest;
import com.poly.datn.dto.response.Pagination;
import com.poly.datn.dto.response.ProductResponse;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CURDProductService {
    Pagination<?> findAll(Pageable pageable);


    ProductResponse create(ProductRequest productRequest);

    ProductResponse update(ProductRequest productRequest);

    void delete(Integer id);


}
