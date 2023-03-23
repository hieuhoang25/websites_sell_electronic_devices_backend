package com.poly.datn.service;

import com.poly.datn.dto.response.AccountResponse;
import com.poly.datn.dto.response.Pagination;

import java.util.List;

import org.springframework.data.domain.Pageable;


public interface CURDAccountService {

    Pagination<?> findAll(Pageable pageable);

    Pagination<?> findAllByFilter(Pageable pageable, String keysearch);

    Pagination<?> findAllByFilterWithDeleted(Pageable pageable,String keysearch,Integer roleId);

    AccountResponse findByUserName(String username);
}
