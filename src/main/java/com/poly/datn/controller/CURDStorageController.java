package com.poly.datn.controller;


import com.poly.datn.service.CURDColorService;
import com.poly.datn.service.CURDStorageService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.poly.datn.controller.router.Router.ADMIN_API.BASE;
import static com.poly.datn.controller.router.Router.ADMIN_API.STORAGE;

@RestController
@RequestMapping(BASE + STORAGE)
@RequiredArgsConstructor
@Tag(name = BASE + STORAGE)
public class CURDStorageController {
    private  final CURDStorageService curdStorageService;
    @GetMapping()
    public ResponseEntity<?> findAll () {
        return ResponseEntity.ok(curdStorageService.findAll());
    }
}
