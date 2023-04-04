package com.poly.datn.service.serviceImpl;

import com.poly.datn.dto.request.LoginRequest;
import com.poly.datn.dto.request.UpdatePasswordRequest;
import com.poly.datn.dto.response.JwtResponse;
import com.poly.datn.entity.Account;
import com.poly.datn.repository.AccountRepository;
import com.poly.datn.service.LoginService;
import com.poly.datn.service.UpdatePasswordService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.validation.annotation.Validated;

@Service
@AllArgsConstructor
@Slf4j
public class UpdatePasswordServiceImpl implements UpdatePasswordService {

    private PasswordEncoder passwordEncoder;
    private  AccountRepository accountRepository;
    private LoginService loginService;
    @Override
    public ResponseEntity<?> updatePassword(UpdatePasswordRequest request) {

        if (!request.getNew_password().equals(request.getConfirm_password())) throw new RuntimeException("Nhập lại mật khẩu không chính xác");
        String name = SecurityContextHolder.getContext().getAuthentication().getName();
        Account account = accountRepository.findByUsername(name);
        log.info("{}", name);
        log.info("{}", account);
        if (!passwordEncoder.matches( request.getOld_password(), account.getPassword())) throw new RuntimeException("Mật khẩu cũ không chính xác");
        String encodeNewPassword= passwordEncoder.encode(request.getNew_password());
        account.setPassword(encodeNewPassword);
        accountRepository.save(account);
        LoginRequest loginRequest = LoginRequest.builder()
                .userName(account.getUsername()).password(request.getNew_password()).build();
        return loginService.login(loginRequest);
    }
}
