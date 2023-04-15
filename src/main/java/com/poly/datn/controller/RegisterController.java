package com.poly.datn.controller;

import com.poly.datn.controller.router.Router;
import com.poly.datn.dto.request.RegisterRequest;
import com.poly.datn.service.RegisterService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import static com.poly.datn.controller.router.Router.API.*;

@RestController
@RequestMapping(Router.API.BASE)
@AllArgsConstructor
@Tag(name = Router.API.BASE + REGISTER)
@Validated
public class RegisterController {

    private RegisterService registerService;

    @PostMapping(REGISTER)
    public ResponseEntity<?> register(@RequestBody @Valid RegisterRequest registerRequest) {
        return ResponseEntity.ok(registerService.register(registerRequest));
    }

    @PostMapping(RESEND_OTP)
    public ResponseEntity<?> resend(@PathVariable("username") String username){
        return ResponseEntity.ok(registerService.reSendOTP(username));
    }

    @PostMapping(VERIFICATION_OTP)
    public ResponseEntity<?> verificationOTP(@PathVariable("username") String username, @PathVariable("code") String code){
        return ResponseEntity.ok(registerService.verifyOtp(code, username));
    }

}
