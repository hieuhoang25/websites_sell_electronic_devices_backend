package com.poly.datn.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "order_status")
public class OrderStatus {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Size(max = 55)
    @Column(name = "name", length = 55)
    private String name;

    @Size(max = 100)
    @Column(name = "title", length = 100)
    private String title;

    @OneToMany(mappedBy = "status")
    private List<Order> orders;
}