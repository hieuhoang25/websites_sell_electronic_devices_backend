package com.poly.datn.service;

import com.poly.datn.dto.response.Pagination;
import org.springframework.data.domain.Pageable;

public interface ProductHomeService {
    Pagination bigDiscount(Pageable pageable);

    Pagination newArrival(Pageable pageable);

    Pagination topSales(Pageable pageable);
}
