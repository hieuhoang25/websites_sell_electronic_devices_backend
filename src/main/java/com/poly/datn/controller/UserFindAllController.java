package com.poly.datn.controller;

import com.poly.datn.controller.router.Router;
import com.poly.datn.dto.response.UserFindAllResponse;
import com.poly.datn.service.UserFindAll;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping(Router.API.BASE)
public class UserFindAllController {
    private final UserFindAll userFindAll;

    @GetMapping
    public ResponseEntity<List<UserFindAllResponse>> userFindAll() {
        return ResponseEntity.ok(userFindAll.getUser());
    }
}
