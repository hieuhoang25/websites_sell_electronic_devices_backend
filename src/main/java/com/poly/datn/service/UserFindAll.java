package com.poly.datn.service;


import com.poly.datn.common.SearchCriteria;
import com.poly.datn.common.SearchResult;
import com.poly.datn.dto.response.ProductResponse;
import com.poly.datn.entity.Product;
import com.poly.datn.entity.User;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface UserFindAll {
    List<User> findAll();

    SearchResult<ProductResponse> findProductsByMultiFiels(List<SearchCriteria> criteria, Pageable pageable);
}
