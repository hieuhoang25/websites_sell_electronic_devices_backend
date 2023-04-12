package com.poly.datn.controller;


import com.poly.datn.dto.response.AccessTokenResponse;
import com.poly.datn.dto.response.DecodeJWTResponse;
import com.poly.datn.dto.response.MessageResponse;
import com.poly.datn.entity.RefreshToken;
import com.poly.datn.repository.AccountRepository;
import com.poly.datn.security.RefreshTokenService;
import com.poly.datn.security.jwt.JwtUtils;
import com.poly.datn.utils.MailUtil;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.*;
import java.util.stream.Collectors;

import static com.poly.datn.controller.router.Router.API.BASE;
import static com.poly.datn.controller.router.Router.API.REFRESH_TOKEN;
import static org.springframework.http.HttpStatus.FORBIDDEN;

@RestController
@RequiredArgsConstructor
@RequestMapping(BASE + REFRESH_TOKEN)
@Tag(name = BASE + REFRESH_TOKEN)
@Slf4j
class RefreshTokenController {
    private final RefreshTokenService refreshTokenService;
    private final JwtUtils jwtUtils;
    private final AccountRepository accountRepository;

    @GetMapping
    public ResponseEntity<?> refreshToken(HttpServletRequest request) {
        String refresh_token = jwtUtils.getJwtRefreshFromCookies(request);
        Optional<RefreshToken> refreshTokenDB = refreshTokenService.findByToken(refresh_token);
        try {
            if (refreshTokenDB.isPresent()) {
                DecodeJWTResponse jwt = jwtUtils.decodeJWT(refresh_token);//this also check valid refresh token
                String name = jwt.getUserName();
                List<String> roles = new ArrayList<>();
                if (MailUtil.validateEmail(name)) {
                    roles.add("USER");
                } else {
                    roles = accountRepository.findByUsername(name).getAuthorities()
                            .stream()
                            .map(role -> role.getRole().getRoleName())
                            .collect(Collectors.toList());
                }
                ResponseCookie jwtCookie = jwtUtils.generateJwtCookieFromName(name, roles);
                return ResponseEntity.ok()
                        .header(HttpHeaders.SET_COOKIE, jwtCookie.toString())
                        .body(new AccessTokenResponse(jwtCookie.getValue()));
            }
        } catch (Exception e) {
            log.info("Error logging in: {}", e.getMessage());
            Map<String, String> error = new HashMap<>();
            error.put("error_message", e.getMessage());
            return ResponseEntity.status(FORBIDDEN.value())
                    .header("error", e.getMessage())
                    .body(error);
        }
        return ResponseEntity.badRequest().body(new MessageResponse("Refresh Token is empty!"));
    }
}