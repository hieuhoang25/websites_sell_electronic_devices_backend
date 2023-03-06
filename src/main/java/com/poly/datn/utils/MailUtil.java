package com.poly.datn.utils;

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
import java.util.Map;

@Component
@RequiredArgsConstructor
public class MailUtil {
    private final SpringTemplateEngine templateEngine;
    private final JavaMailSender emailSender;


   public void sendOrderStatus(String id, String status, String mail) throws MessagingException {
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
        map.put("id", id);
        map.put("status",status);
        map.put("name","Trương Hoàng Long");
        context.setVariables(map);
        String html = templateEngine.process("order-status-templates", context);
        helper.setTo(mail);
        helper.setText(html, true);
        helper.setSubject("Trạng thái đơn hàng của bạn");
        emailSender.send(message);

    }
    @Scheduled(cron = "@weekly")
    public void sendPromotionForUser(){
        //...
    }
}
