package com.poly.datn.repository;

import com.poly.datn.entity.Notification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotificationRepository extends JpaRepository<Notification, Integer> {
}
