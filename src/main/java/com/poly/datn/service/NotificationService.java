package com.poly.datn.service;

import com.poly.datn.dto.response.NotificationResponse;

import java.util.List;

public interface NotificationService {

    List<NotificationResponse> getAll();
    List<NotificationResponse> delete(Integer id);
    List<NotificationResponse> deleteAll();
}
