package com.poly.datn.controller;

import com.poly.datn.controller.router.Router;
import com.poly.datn.dto.response.ProductStorageResponse;
import com.poly.datn.service.ProductStorageService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.poly.datn.controller.router.Router.API.STORAGE;

@RequiredArgsConstructor
@RestController
@RequestMapping(Router.API.BASE + STORAGE)
@Tag(name = Router.API.BASE + STORAGE)
public class StorageFindAllController {
    private final ProductStorageService service;
    @GetMapping
    public ResponseEntity<List<ProductStorageResponse>> getStorage(){
        return ResponseEntity.ok(service.getStorage());
    }
}
