package com.poly.datn.utils;

import com.poly.datn.dto.response.OrderDetailResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class MailUtil {
    private final SpringTemplateEngine templateEngine;
    private final JavaMailSender emailSender;


   public void sendOrderStatus(String fullname, String status, String mail, List<OrderDetailResponse> orderDetails) throws MessagingException {
        MimeMessage message = emailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(
                message,
                MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED,
                StandardCharsets.UTF_8.name()
        );
        // add attachment file into html file
//        helper.addAttachment("template-cover.png", new ClassPathResource("javabydeveloper-email.PNG"));

        // Context: to input properties to parameter be written into a file
        Context context = new Context();// the context is a class of thymeleaf package
        Map<String, Object> map = new HashMap<>();
        map.put("status",status);
        map.put("fullname",fullname);
        map.put("orderDetails",orderDetails);
        context.setVariables(map);
        String html = templateEngine.process("order-status-templates", context);
        helper.setTo(mail);
        helper.setText(html, true);
        helper.setSubject("Trạng thái đơn hàng của bạn");
        emailSender.send(message);

    }
    public void sendEmailVerification(String code, String email) throws MessagingException {
        MimeMessage message = emailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(
                message,
                MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED,
                StandardCharsets.UTF_8.name()
        );
        // add attachment file into html file
//        helper.addAttachment("template-cover.png", new ClassPathResource("javabydeveloper-email.PNG"));

        // Context: to input properties to parameter be written into a file
        Context context = new Context();// the context is a class of thymeleaf package
        Map<String, Object> map = new HashMap<>();
        map.put("otp", code);
        context.setVariables(map);
        String html = templateEngine.process("email-vertification-templates", context);
        helper.setTo(email);
        helper.setText(html, true);
        helper.setSubject("Xác minh tài khoản của bạn");
        emailSender.send(message);
    }

    public void sendEmailThankLetter(String fullname, String email ) throws MessagingException {
        MimeMessage message = emailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(
                message,
                MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED,
                StandardCharsets.UTF_8.name()
        );
        // add attachment file into html file
//        helper.addAttachment("template-cover.png", new ClassPathResource("javabydeveloper-email.PNG"));

        // Context: to input properties to parameter be written into a file
        Context context = new Context();// the context is a class of thymeleaf package
        Map<String, Object> map = new HashMap<>();
        map.put("fullname", fullname);
        context.setVariables(map);
        String html = templateEngine.process("thank-letter", context);
        helper.setTo(email);
        helper.setText(html, true);
        helper.setSubject("Xác minh tài khoản của bạn");
        emailSender.send(message);
    }

    public void sendOrderStatusMail(String fullname, String email , String orderStatus) throws MessagingException {
        MimeMessage message = emailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(
                message,
                MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED,
                StandardCharsets.UTF_8.name()
        );
        // add attachment file into html file
//        helper.addAttachment("template-cover.png", new ClassPathResource("javabydeveloper-email.PNG"));

        // Context: to input properties to parameter be written into a file
        Context context = new Context();// the context is a class of thymeleaf package
        Map<String, Object> map = new HashMap<>();
        map.put("fullname", fullname);
        map.put("orderStatus", orderStatus);
        context.setVariables(map);
        String html = templateEngine.process("order-status-templates", context);
        helper.setTo(email);
        helper.setText(html, true);
        helper.setSubject("Trạng thái đơn hàng của bạn");
        emailSender.send(message);
    }

    @Scheduled(cron = "@weekly")
    public void sendPromotionForUser(){
        //...
    }

    public static Boolean validateEmail(String email){
       String regexPattern = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@"
               + "[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";
       return email.matches(regexPattern);
    }
}
