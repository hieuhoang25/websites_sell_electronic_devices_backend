package com.poly.datn.controller;

import com.poly.datn.controller.router.Router;
import com.poly.datn.dto.response.UserLoggedResponse;
import com.poly.datn.service.UserInfoByTokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.poly.datn.controller.router.Router.USER_API.INFO;

@RequiredArgsConstructor
@RestController
@RequestMapping(Router.USER_API.BASE + INFO)
public class UserInfoByTokenController {
    private final UserInfoByTokenService service;

    @GetMapping
    public ResponseEntity<UserLoggedResponse> getUserInfo(){
        return ResponseEntity.ok(service.getUserInfo());
    }
}
