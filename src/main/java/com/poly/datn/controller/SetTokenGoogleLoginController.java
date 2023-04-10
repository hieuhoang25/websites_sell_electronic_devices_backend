package com.poly.datn.controller;

import com.poly.datn.controller.router.Router;
import com.poly.datn.dto.response.JwtResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Arrays;
import java.util.List;

import static com.poly.datn.controller.router.Router.API.TOKEN_LOGIN_GOOGLE;

@RestController
@RequestMapping(Router.API.BASE + TOKEN_LOGIN_GOOGLE)
@Validated
@RequiredArgsConstructor
public class SetTokenGoogleLoginController {
    @Value("${spring.app.jwtCookieName}")
    private String jwtCookie;

    @Value("${spring.app.jwtRefreshCookieName}")
    private String jwtRefreshCookie;

    @GetMapping
    public ResponseEntity<?> setTokenForGoogleLogin(
            @RequestParam @Valid @NotBlank @NotNull String accessToken,
            @RequestParam @Valid @NotBlank @NotNull String refreshToken) {
        ResponseCookie accessTokenCookie = ResponseCookie.from(jwtCookie, accessToken)
                .path("/api").maxAge(24 * 60 * 60 * 30)
                .httpOnly(true).sameSite("None").secure(true).build();
        ResponseCookie refreshTokenCookie = ResponseCookie.from(jwtRefreshCookie, refreshToken)
                .path("/api").maxAge(24 * 60 * 60 * 30).
                httpOnly(true).sameSite("None").secure(true).build();
        List<String> roles = Arrays.asList("USER");
        return ResponseEntity.ok().
                header(HttpHeaders.SET_COOKIE, accessTokenCookie.toString(), refreshTokenCookie.toString())
                .body(new JwtResponse(accessTokenCookie.getValue(), roles));
    }
}
