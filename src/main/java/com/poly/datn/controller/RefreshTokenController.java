 package com.poly.datn.controller;

 
 import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.poly.datn.dto.response.AccessTokenResponse;
 import com.poly.datn.dto.response.MessageResponse;
import com.poly.datn.entity.RefreshToken;
import com.poly.datn.exception.TokenRefreshException;
 import com.poly.datn.security.RefreshTokenService;
import com.poly.datn.security.jwt.JwtUtils;

import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;

import lombok.RequiredArgsConstructor;
import io.swagger.v3.oas.annotations.tags.Tag;

import static com.poly.datn.controller.router.Router.API.*;

import javax.servlet.http.HttpServletRequest;
 
@RestController
@RequiredArgsConstructor
@RequestMapping(BASE + REFRESH_TOKEN)
@Tag(name = BASE+ REFRESH_TOKEN)
class RefreshTokenController {
    private final RefreshTokenService refreshTokenService;
    private final JwtUtils jwtUtils;
    

@PostMapping()
  public ResponseEntity<?> refreshtoken(HttpServletRequest request) {
      String refreshToken = jwtUtils.getJwtRefreshFromCookies(request);
  
    if ((refreshToken != null) && (refreshToken.length() > 0)) {
      return refreshTokenService.findByToken(refreshToken)
          .map(refreshTokenService::verifyExpiration)
          .map(RefreshToken::getAccount)
          .map(account -> {
            ResponseCookie jwtCookie = jwtUtils.generateJwtCookie(account.getUsername());
            
            return ResponseEntity.ok()
                .header(HttpHeaders.SET_COOKIE, jwtCookie.toString())
                .body(new AccessTokenResponse(jwtCookie.getValue()));
          })
          .orElseThrow(() -> new TokenRefreshException(refreshToken,
              "Refresh token is not in database!"));
    }
    
    return ResponseEntity.badRequest().body(new MessageResponse("Refresh Token is empty!"));
  }
}