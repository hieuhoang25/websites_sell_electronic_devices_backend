package com.poly.datn.dto.response;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Data
public class NotificationResponse {

    private Integer id;

    private Map<String, String> icon;
    private String heading;

    private String title;

    private String path;

    private String subtitle;

    private LocalDateTime timestamp;

    public NotificationResponse() {
        Map<String, String> map = new HashMap<>();
        map.put("color", "error");
        map.put("name", "notifications");
        this.icon = map;
    }
}
