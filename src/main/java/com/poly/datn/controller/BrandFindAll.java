package com.poly.datn.controller;

import com.poly.datn.controller.router.Router;
import com.poly.datn.service.CRUDBrandService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.poly.datn.controller.router.Router.API.BRAND;

@RestController
@RequiredArgsConstructor
@RequestMapping(Router.API.BASE + BRAND)
@Tag(name = Router.API.BASE + BRAND)
public class BrandFindAll {
    private final CRUDBrandService service;
    @GetMapping()
    public ResponseEntity<?> getBrand(){
        return ResponseEntity.ok(service.findAll());
    }
}
