package com.poly.datn.controller;


import com.poly.datn.service.CURDColorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.poly.datn.controller.router.Router.ADMIN_API.BASE;
import static com.poly.datn.controller.router.Router.ADMIN_API.COLOR;
@RestController
@RequestMapping(BASE + COLOR)
@RequiredArgsConstructor
public class CURDColorController {
    private  final CURDColorService curdColorService;
    @GetMapping()
    public ResponseEntity<?> findAll (){
        return ResponseEntity.ok(curdColorService.findAll());
    }
}
