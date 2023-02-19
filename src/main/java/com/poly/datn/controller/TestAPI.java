package com.poly.datn.controller;


import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/")
public class TestAPI {
    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    @GetMapping("test-log")
    public ResponseEntity<?> test(){
    return ResponseEntity.ok("Test");
    }
}
