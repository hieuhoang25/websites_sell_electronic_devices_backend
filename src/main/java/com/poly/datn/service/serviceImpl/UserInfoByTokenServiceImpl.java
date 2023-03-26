package com.poly.datn.service.serviceImpl;

import com.poly.datn.common.mapper.ModelConverter;
import com.poly.datn.dto.response.UserLoggedResponse;
import com.poly.datn.entity.Account;
import com.poly.datn.entity.User;
import com.poly.datn.repository.AccountRepository;
import com.poly.datn.repository.UserRepository;
import com.poly.datn.service.UserInfoByTokenService;
import com.poly.datn.utils.MailUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

@Service
@RequiredArgsConstructor
public class UserInfoByTokenServiceImpl implements UserInfoByTokenService {
    private final AccountRepository accountRepository;
    private final ModelConverter converter;
    private final UserRepository userRepository;

    @Override
    public UserLoggedResponse getUserInfo() {
        UserLoggedResponse response = converter.map(getCurrentUser(), UserLoggedResponse.class);
        return response;
    }

    @Override
    public User getCurrentUser() {
        String name = SecurityContextHolder.getContext().getAuthentication().getName();
        if (MailUtil.validateEmail(name)) {
            User user = userRepository.findByEmail(name).get();
            if (user == null)
                throw new NotFoundException("user not found");
            return user;
        }
        Account account = accountRepository.findByUsername(name);
        if (account == null)
            throw new NotFoundException("user not found");
        return account.getUser();
    }
}
