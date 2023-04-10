package com.poly.datn.controller;

import com.poly.datn.controller.router.Router;
import com.poly.datn.dto.request.LoginRequest;
import com.poly.datn.service.LoginService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

import static com.poly.datn.controller.router.Router.API.LOGIN;

@RequestMapping(Router.API.BASE + LOGIN)
@RestController
@RequiredArgsConstructor
@Tag(name = Router.API.BASE + LOGIN)
public class LoginController {
    private final LoginService service;
    @PostMapping
    public ResponseEntity<?> login(@RequestBody @Valid LoginRequest request){
        return service.login(request);
    }
}
