package com.poly.datn.controller;

import com.poly.datn.DTO.response.GenericResponse;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/")
public class TestAPI {
    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    @GetMapping("test-log")
    public GenericResponse<String> getLog(){
        GenericResponse<String> response = new GenericResponse();
        response.setStatus(200);
        response.setMessage("Test pass");
        response.setData(null);
        LOGGER.info("Test log passed");

        return response;
    }
}
