package com.poly.datn.service;

import com.poly.datn.dto.response.OrderDetailResponse;

import java.util.List;

public interface MailService {

    void sendOrderStatus(String id, String status, String mail, List<OrderDetailResponse> list);
    void sendEmailVerification(String code, String email);
    void sendEmailThankLetter(String fullname, String email);
    void sendOrderStatusMail(String fullname, String email , String orderStatus);

}
