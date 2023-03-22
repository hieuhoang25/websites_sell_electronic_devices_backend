package com.poly.datn.controller;

import com.poly.datn.controller.router.Router;
import com.poly.datn.dto.response.MessageResponse;
import com.poly.datn.security.RefreshTokenService;
import com.poly.datn.security.UserPrincipal;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static com.poly.datn.controller.router.Router.API.LOGOUT;

@RestController
@RequiredArgsConstructor
@RequestMapping(Router.API.BASE + LOGOUT)
public class LogoutController {


    private  final RefreshTokenService refreshTokenService;
    @PostMapping()
    public ResponseEntity<?> logoutUser(@RequestParam("token") String refresh_token) {
        refreshTokenService.deleteByToken(refresh_token);
        return ResponseEntity.ok(new MessageResponse("Logout successfully"));
    }
}
