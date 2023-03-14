package com.poly.datn.entity;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;


import javax.persistence.*;
import javax.validation.constraints.Size;
import java.time.Instant;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Data
@Table(name = "product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Size(max = 255)
    @Column(name = "product_name")
    private String productName;


    @Size(max = 1000)
    @Column(name = "description")
    private String description;

//    @Column(name = "create_date")
    @Column(name= "create_date", nullable = false, updatable = false)
    @CreationTimestamp
    private Instant createDate;


    @Column(name = "update_date")
    @UpdateTimestamp
    private Instant updateDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category category;

    @Column(name = "is_delete")
    private Boolean isDelete;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "brand_id")
    private Brand brand;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "promotion_id")
    private PromotionProduct promotion;

    @Column(name = "type")
    private Integer type;

    @Size(max = 255)
    @Column(name = "image")
    private String image;

    @OneToMany(mappedBy = "product")
    private Set<Wishlist> wishlists = new LinkedHashSet<>();

    @OneToMany(mappedBy = "product")
    private Set<ProductAttribute> productAttributes = new LinkedHashSet<>();

    @OneToMany(mappedBy = "product")
    private Set<Rating> ratings = new LinkedHashSet<>();

    @OneToMany(mappedBy = "product")
    private Set<ProductVariant> productVariants = new LinkedHashSet<>();

    @PostPersist
    private void postCreate(){
        this.image = "product-"+this.id+".png";
    }
}