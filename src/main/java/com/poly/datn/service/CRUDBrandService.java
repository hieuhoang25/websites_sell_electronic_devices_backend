package com.poly.datn.service;


import com.poly.datn.dto.request.BrandRequest;
import com.poly.datn.dto.response.BrandResponse;
import com.poly.datn.dto.response.ListProductOfBrand;

import java.util.List;

public interface CRUDBrandService {
    List<BrandResponse> findAll();

    BrandResponse createBranch(BrandRequest brandRequest);

    BrandResponse updateById(BrandRequest brandRequest);

    ListProductOfBrand listProductOfBrand(Integer id);

    void delete(Integer id);
}
