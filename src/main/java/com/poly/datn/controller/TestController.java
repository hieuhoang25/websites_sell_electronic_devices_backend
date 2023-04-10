package com.poly.datn.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.poly.datn.entity.Notification;
import com.poly.datn.repository.NotificationRepository;
import lombok.AllArgsConstructor;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@AllArgsConstructor
public class TestController {
    final SimpMessageSendingOperations messagingTemplate;
    final NotificationRepository notificationRepository;
    @GetMapping("/api/un/test-socket")
    public void test() throws JsonProcessingException {
        Notification notification = new Notification();
        notification.setHeading("Thông báo đơn hàng");
        notification.setSubtitle("Số lượng sản phẩm: "+ 11);
        notification.setPath("order");
        notification.setTitle("Khách hàng " + "Hieu Hoang" + " đã đặt hàng !");
        notificationRepository.save(notification);
        Gson gson = new Gson();
        this.messagingTemplate.convertAndSend("/topic/server", "Khách hàng " + "Hiếu Hoàng" + " đã đặt hàng");
        this.messagingTemplate.convertAndSend("/topic/notifications", gson.toJson(notificationRepository.findAll()));

    }

    @GetMapping("/api/un/test-notification")
    public void tes1t(){
        Notification notification = new Notification();
        notification.setHeading("Alert");
        notification.setSubtitle("Traffice reached 2M");
        notification.setPath("page-layouts/user-profile");
        notification.setTitle("Server overloaded");
        Notification notification2 = new Notification();
        notification.setHeading("Alert");
        notification.setSubtitle("Traffice reached 2M");
        notification.setPath("page-layouts/user-profile");
        notification.setTitle("Server overloaded");
        notificationRepository.save(notification);
        notificationRepository.save(notification2);
    }
}
