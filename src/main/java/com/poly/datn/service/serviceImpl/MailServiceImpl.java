package com.poly.datn.service.serviceImpl;

import com.poly.datn.dto.response.OrderDetailResponse;
import com.poly.datn.service.MailService;
import com.poly.datn.utils.MailUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MailServiceImpl implements MailService {

    private final MailUtil mailUtil;

    @Async
    @Override
    public void sendOrderStatus(String id, String status, String mail, List<OrderDetailResponse> list) {
        try {
            mailUtil.sendOrderStatus(id, status, mail, list);
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }


    @Async
    @Override
    public void sendEmailVerification(String code, String email) {
        try {
            mailUtil.sendEmailVerification(code,email);
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    @Async
    public void sendEmailThankLetter(String fullname, String email) {
        try {
            mailUtil.sendEmailThankLetter(fullname,email);
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    @Async
    public void sendOrderStatusMail(String fullname, String email, String orderStatus) {
        try {
            mailUtil.sendOrderStatusMail(fullname,email, orderStatus );
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }


}
