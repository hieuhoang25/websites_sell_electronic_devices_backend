package com.poly.datn.service.serviceImpl;

import com.poly.datn.common.mapper.ModelConverter;
import com.poly.datn.dto.response.AuthorityResponse;
import com.poly.datn.repository.AuthorityRepository;
import com.poly.datn.service.AuthorityService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class AuthorityServiceImpl implements AuthorityService {
    private final ModelConverter modelConverter;
    private final AuthorityRepository authorityRepository;
    @Override
    public void setRole(Integer accountId,Integer roleId) {
         authorityRepository.setRole(accountId,roleId);
    }
}
