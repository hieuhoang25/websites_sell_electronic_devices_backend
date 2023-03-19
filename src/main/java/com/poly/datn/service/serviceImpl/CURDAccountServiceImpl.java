package com.poly.datn.service.serviceImpl;

import com.poly.datn.common.mapper.ModelConverter;
import com.poly.datn.dto.response.AccountResponse;
import com.poly.datn.dto.response.AuthorityResponse;
import com.poly.datn.dto.response.Pagination;
import com.poly.datn.dto.response.RoleResponse;
import com.poly.datn.repository.AccountRepository;
import com.poly.datn.repository.AuthorityRepository;
import com.poly.datn.service.CURDAccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CURDAccountServiceImpl implements CURDAccountService {
    private  final ModelConverter modelConverter;
    private  final AccountRepository accountRepository;

    private final AuthorityRepository authorityRepository;

    @Override
    public Pagination<?> findAll(Pageable pageable) {
        Integer size = pageable.getPageSize();
        Integer totalPages= Math.ceil((float)accountRepository.findAll().stream().count()/size)==0
                ? 1: (int) Math.ceil((float)accountRepository.findAll().stream().count()/size);
        return new Pagination<AccountResponse>(
                pageable.getPageSize(),
                pageable.getPageNumber(),
                totalPages,
                modelConverter.mapAllByIterator(accountRepository.findAllUserAndAuth(pageable),AccountResponse.class)
        );
    }

  
}
