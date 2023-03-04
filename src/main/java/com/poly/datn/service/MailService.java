package com.poly.datn.service;

public interface MailService {

    void sendOrderStatus(String id, String status, String mail);
    void sendEmailVerification();

}
