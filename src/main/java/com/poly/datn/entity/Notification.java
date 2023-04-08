package com.poly.datn.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "notification")
public class Notification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String heading;

    private String title;

    private String path;

    private String subtitle;

    @CreationTimestamp
    private LocalDateTime timestamp;

}
