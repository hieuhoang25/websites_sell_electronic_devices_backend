package com.poly.datn.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.poly.datn.entity.Notification;
import com.poly.datn.repository.NotificationRepository;
import com.poly.datn.service.OrderService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

@RestController
@AllArgsConstructor
public class TestController {
    final SimpMessageSendingOperations messagingTemplate;
    final NotificationRepository notificationRepository;
    private OrderService orderService;
    @GetMapping("/api/un/test-socket")
    public ResponseEntity<?> test() throws JsonProcessingException {
        return ResponseEntity.ok(orderService.countOrdersByStatus());
    }

    @GetMapping("/api/un/test-notification")
    public ResponseEntity<?>  tes1t(@RequestParam("status")List<String> status){
        return ResponseEntity.ok(orderService.findAll(status));
    }
}
