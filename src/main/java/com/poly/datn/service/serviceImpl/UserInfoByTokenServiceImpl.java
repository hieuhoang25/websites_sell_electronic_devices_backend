package com.poly.datn.service.serviceImpl;

import com.poly.datn.common.mapper.ModelConverter;
import com.poly.datn.dto.response.UserLoggedResponse;
import com.poly.datn.entity.Account;
import com.poly.datn.entity.User;
import com.poly.datn.repository.AccountRepository;
import com.poly.datn.service.UserInfoByTokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

@Service
@RequiredArgsConstructor
public class UserInfoByTokenServiceImpl implements UserInfoByTokenService {
    private final AccountRepository accountRepository;
    private final ModelConverter converter;

    @Override
    public UserLoggedResponse getUserInfo() {
        String userName = SecurityContextHolder.getContext().getAuthentication().getName();
        Account account = accountRepository.findByUsername(userName);
        if(account == null)
            throw new NotFoundException("user not found");
        User user = account.getUser();
        UserLoggedResponse response = converter.map(user,UserLoggedResponse.class);
        return response;
    }

    @Override
    public User getCurrentUser() {
        String userName = SecurityContextHolder.getContext().getAuthentication().getName();
        Account account = accountRepository.findByUsername(userName);
        return  account == null? null : account.getUser();
    }
}
