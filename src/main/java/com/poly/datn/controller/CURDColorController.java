package com.poly.datn.controller;


import com.poly.datn.service.CURDColorService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.poly.datn.controller.router.Router.ADMIN_API.*;

@RestController
@RequestMapping(BASE + COLOR)
@RequiredArgsConstructor
@Tag(name = BASE + COLOR)
public class CURDColorController {
    private  final CURDColorService curdColorService;
    @GetMapping()
    public ResponseEntity<?> findAll (){
        return ResponseEntity.ok(curdColorService.findAll());
    }
}
