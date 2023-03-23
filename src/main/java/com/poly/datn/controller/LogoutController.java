package com.poly.datn.controller;

import com.poly.datn.controller.router.Router;
import com.poly.datn.dto.response.MessageResponse;
import com.poly.datn.security.jwt.JwtUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.poly.datn.controller.router.Router.API.LOGOUT;

@RestController
@RequiredArgsConstructor
@RequestMapping(Router.API.BASE + LOGOUT)
public class LogoutController {
    private final JwtUtils jwtUtils;

    @PostMapping()
    public ResponseEntity<?> logoutUser() {
        ResponseCookie cookie = jwtUtils.getCleanJwtCookie();
        ResponseCookie refreshToken = jwtUtils.getCleanJwtRefreshCookie();
        return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, cookie.toString()).header(HttpHeaders.SET_COOKIE,refreshToken.toString())
            .body(new MessageResponse("You've been signed out!"));
    }
}
