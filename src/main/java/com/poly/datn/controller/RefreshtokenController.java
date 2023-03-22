 package com.poly.datn.controller;

 
 import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.poly.datn.dto.request.TokenRefreshRequest;
import com.poly.datn.dto.response.TokenRefreshResponse;
import com.poly.datn.entity.RefreshToken;
import com.poly.datn.exception.TokenRefreshException;
import com.poly.datn.repository.AccountRepository;
import com.poly.datn.security.JwtUtils;
import com.poly.datn.security.RefreshTokenService;

import lombok.RequiredArgsConstructor;
import io.swagger.v3.oas.annotations.tags.Tag;

import static com.poly.datn.controller.router.Router.API.*;

import javax.validation.Valid;
 
@RestController
@RequiredArgsConstructor
@RequestMapping(BASE + REFRESH_TOKEN)
@Tag(name = BASE+ REFRESH_TOKEN)
class RefreshtokenController {
    private final RefreshTokenService refreshTokenService;
    private final JwtUtils jwtUtils;
    private final AccountRepository repository;
    

@PostMapping()
  public ResponseEntity<?> refreshtoken(@Valid @RequestBody TokenRefreshRequest request) {
    String requestRefreshToken = request.getRefresh_token();
  
    return refreshTokenService.findByToken(requestRefreshToken)
        .map(refreshTokenService::verifyExpiration)
        .map(RefreshToken::getAccount)
        .map(account -> {
        
          String token = jwtUtils.generateTokenFromUsername(account.getUsername());
          return ResponseEntity.ok(new TokenRefreshResponse(token, requestRefreshToken));
        })
        .orElseThrow(() -> new TokenRefreshException(requestRefreshToken,
            "Refresh token is not in database!"));
  }
}