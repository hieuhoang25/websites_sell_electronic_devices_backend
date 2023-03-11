package com.poly.datn.controller;

import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.*;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class NotificationController {
    @Autowired
    private SimpMessageSendingOperations messagingTemplate;

    // use for check server live or die
    @MessageExceptionHandler
    public String handleException(Throwable exception) {
        messagingTemplate.convertAndSend("/errors", exception.getMessage());
        return exception.getMessage();
    }
}
