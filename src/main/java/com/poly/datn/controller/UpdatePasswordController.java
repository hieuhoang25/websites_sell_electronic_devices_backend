package com.poly.datn.controller;

import com.poly.datn.controller.router.Router;
import com.poly.datn.dto.request.UpdatePasswordRequest;
import com.poly.datn.service.UpdatePasswordService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

import static com.poly.datn.controller.router.Router.USER_API.CHANGE_PASSWORD;

@RequiredArgsConstructor
@RestController
@RequestMapping(Router.USER_API.BASE + CHANGE_PASSWORD)
@Slf4j
public class UpdatePasswordController {

    private final UpdatePasswordService updatePasswordService;

    @PostMapping
    public ResponseEntity<?> updatePassword(@RequestBody @Valid  UpdatePasswordRequest request){
            log.info("{}", request);
            return updatePasswordService.updatePassword(request);
    }

}
