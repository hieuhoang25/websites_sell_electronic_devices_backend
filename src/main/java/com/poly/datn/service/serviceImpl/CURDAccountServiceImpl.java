package com.poly.datn.service.serviceImpl;

import com.poly.datn.common.mapper.ModelConverter;
import com.poly.datn.dto.response.*;
import com.poly.datn.repository.AccountRepository;
import com.poly.datn.repository.AuthorityRepository;
import com.poly.datn.service.CURDAccountService;
import lombok.RequiredArgsConstructor;


import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class CURDAccountServiceImpl implements CURDAccountService {
    private  final ModelConverter modelConverter;
    private  final AccountRepository accountRepository;


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

    @Override
    public Pagination<?> findAllByFilter(Pageable pageable, String keysearch) {
        Integer size = pageable.getPageSize();
        Integer totalPages= Math.ceil((float)accountRepository.countAccountByFilters(keysearch)/size)==0
                ? 1: (int) Math.ceil((float)accountRepository.countAccountByFilters(keysearch)/size);
        return new Pagination<AccountResponse>(
                pageable.getPageSize(),
                pageable.getPageNumber(),
                totalPages,
                modelConverter.mapAllByIterator(accountRepository.findAllByFilters(pageable,keysearch),AccountResponse.class)
        );
    }

    @Override
    public Pagination<?> findAllByFilterWithDeleted(Pageable pageable, String keysearch, Integer roleId) {
        Integer size = pageable.getPageSize();
        Integer totalPages= Math.ceil((float)accountRepository.countAccountByFiltersWithRole(keysearch,roleId)/size)==0
                ? 1: (int) Math.ceil((float)accountRepository.countAccountByFiltersWithRole(keysearch,roleId)/size);
        System.out.println(totalPages);
        return new Pagination<AccountResponse>(
                pageable.getPageSize(),
                pageable.getPageNumber(),
                totalPages,
                modelConverter.mapAllByIterator(accountRepository.findAllByFilterWithRole(pageable,keysearch,roleId),AccountResponse.class)
        );
    }

@Override
public AccountResponse findByUserName(String username) {
        return modelConverter.map(accountRepository.findByUsername(username), AccountResponse.class) ;
}

// @Override
// public List<?> findByUsername(String username) {
       
//         return modelConverter.mapAllByIterator(accountRepository.findByUsername(username), null)
// }


}
