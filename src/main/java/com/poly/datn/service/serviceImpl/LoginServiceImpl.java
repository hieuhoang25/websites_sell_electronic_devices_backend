package com.poly.datn.service.serviceImpl;

import com.poly.datn.dto.request.LoginRequest;
import com.poly.datn.entity.Account;
import com.poly.datn.repository.AccountRepository;
import com.poly.datn.security.TokenProvider;
import com.poly.datn.service.LoginService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class LoginServiceImpl implements LoginService {
    private final AccountRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final TokenProvider tokenProvider;

    @Override
    public Map<String, String> login(LoginRequest request) {
        Map<String, String> map = new HashMap<>();
        Account account = repository.findByUsername(request.getUserName());
        if (account == null) {
            map.put("error", "Tài hoặc mật khẩu không chính xác");
            return map;
        } else if (!passwordEncoder.matches(request.getPassword(),passwordEncoder.encode(account.getPassword()))) {
            map.put("error", "Tài hoặc mật khẩu không chính xác");
            return map;
        }
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(request.getUserName(), request.getPassword());
        Authentication authentication = authenticationManager.authenticate(authenticationToken);//xác minh người dùng
        String access_token = tokenProvider.createToken(authentication);
        map.put("access_token", access_token);
        return map;
    }
}
