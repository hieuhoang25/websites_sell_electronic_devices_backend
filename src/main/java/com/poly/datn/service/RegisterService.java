package com.poly.datn.service;


import com.poly.datn.dto.request.RegisterRequest;
import com.poly.datn.dto.response.MessageVerification;

public interface RegisterService {

    MessageVerification register(RegisterRequest registerRequest);

    MessageVerification verifyOtp(String otp, String email);

    MessageVerification reSendOTP(String email);

}
