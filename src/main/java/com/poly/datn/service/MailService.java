package com.poly.datn.service;

public interface MailService {

    void sendOrderStatus(String id, String status, String mail);
    void sendEmailVerification(String code, String email);
    void sendEmailThankLetter(String fullname, String email);
    void sendOrderStatusMail(String fullname, String email , String orderStatus);
}
