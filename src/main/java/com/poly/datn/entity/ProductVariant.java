package com.poly.datn.entity;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.LinkedHashSet;
import java.util.Set;

@Data
@Entity
@Table(name = "product_variant")
public class ProductVariant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Size(max = 255)
    @Column(name = "sku_name")
    private String skuName;

    @Column(name = "quantity")
    private Integer quantity;

    @Column(name = "price")
    private Double price;

    @Column(name = "status")
    private Boolean status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product product;

    @Size(max = 255)
    @Column(name = "image")
    private String image;

    @Size(max = 255)
    @Column(name = "display_name")
    private String displayName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "color_id")
    private Color color;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "storage_id")
    private Storage storage;

    @OneToMany(mappedBy = "productVariant")
    private Set<CartDetail> cartDetails = new LinkedHashSet<>();

    @OneToMany(mappedBy = "productVariant")
    private Set<OrderDetail> orderDetails = new LinkedHashSet<>();

    @PostPersist
    private void AfterCreater(){
        this.image = "productVariant-" + this.id;
    }

}