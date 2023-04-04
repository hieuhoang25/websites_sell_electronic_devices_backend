package com.poly.datn.service;

import com.poly.datn.common.SearchCriteria;
import com.poly.datn.common.SearchResult;
import com.poly.datn.dto.response.ProductFilterResponse;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProductFindByMultiField {
    SearchResult<ProductFilterResponse>
    findByMultiField(List<SearchCriteria> criteria,
                     Pageable pageable,
                     String sortField,
                     String sortType);
}
