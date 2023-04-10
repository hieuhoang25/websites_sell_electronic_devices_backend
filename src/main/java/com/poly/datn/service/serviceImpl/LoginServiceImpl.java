package com.poly.datn.service.serviceImpl;

import com.poly.datn.dto.request.LoginRequest;
import com.poly.datn.dto.response.JwtResponse;
import com.poly.datn.entity.Account;
import com.poly.datn.repository.AccountRepository;
import com.poly.datn.security.RefreshTokenService;
import com.poly.datn.security.jwt.JwtUtils;
import com.poly.datn.service.LoginService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
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
    private final RefreshTokenService refreshTokenService;
    private final JwtUtils jwtUtils;

    @Override
    public ResponseEntity<?> login(LoginRequest request) {
        Map<String, String> map = new HashMap<>();
        Account account = repository.findByUsername(request.getUserName());
        if (account == null) {
            map.put("error", "Tài hoặc mật khẩu không chính xác");
            return ResponseEntity.badRequest().body(map);
        } else if (!passwordEncoder.matches(request.getPassword(), account.getPassword())) {
            map.put("error", "Tài hoặc mật khẩu không chính xác");
            return ResponseEntity.badRequest().body(map);
        }
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(request.getUserName(), request.getPassword());
        Authentication authentication = authenticationManager.authenticate(authenticationToken);//xác minh người dùng

       
        //create access_token and set to httponly cookie
        ResponseCookie jwtCookie = jwtUtils.generateJwtCookie(authentication);
        String refresh_token = jwtUtils.createRefreshToken(account.getUsername());//create refresh_token
        ResponseCookie refreshCookie = refreshTokenService.generateRefreshTokenCookie(account.getUser(), refresh_token);
        refreshTokenService.deleteTokenByUserIdLimit(account.getUserId());
        return ResponseEntity.ok().
                header(HttpHeaders.SET_COOKIE, jwtCookie.toString(), refreshCookie.toString())
                .body(new JwtResponse(jwtCookie.getValue(), authentication.getAuthorities()));
    }
}
