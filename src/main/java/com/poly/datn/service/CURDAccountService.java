package com.poly.datn.service;

import com.poly.datn.dto.response.AuthorityResponse;
import com.poly.datn.dto.response.Pagination;
import com.poly.datn.dto.response.RoleResponse;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CURDAccountService {

    Pagination<?> findAll(Pageable pageable);

    Pagination<?> findAllByFilter(Pageable pageable, String keysearch);

    Pagination<?> findAllByFilterWithDeleted(Pageable pageable,String keysearch,Integer roleId);
}
