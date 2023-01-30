package com.poly.datn.entity;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.time.Instant;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "promotion_product")
public class PromotionProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "expriation_date")
    private Instant expriationDate;

    @Column(name = "created_date")
    private Instant createdDate;

    @Size(max = 255)
    @Column(name = "name")
    private String name;

    @Column(name = "updated_date")
    private Instant updatedDate;

    @Column(name = "maximum_price")
    private Double maximumPrice;

    @Column(name = "activate")
    private Boolean activate;

    @OneToMany(mappedBy = "promotion")
    private Set<Product> products = new LinkedHashSet<>();

    @OneToMany(mappedBy = "promotion")
    private Set<Category> categories = new LinkedHashSet<>();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Instant getExpriationDate() {
        return expriationDate;
    }

    public void setExpriationDate(Instant expriationDate) {
        this.expriationDate = expriationDate;
    }

    public Instant getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Instant createdDate) {
        this.createdDate = createdDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Instant getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(Instant updatedDate) {
        this.updatedDate = updatedDate;
    }

    public Double getMaximumPrice() {
        return maximumPrice;
    }

    public void setMaximumPrice(Double maximumPrice) {
        this.maximumPrice = maximumPrice;
    }

    public Boolean getActivate() {
        return activate;
    }

    public void setActivate(Boolean activate) {
        this.activate = activate;
    }

    public Set<Product> getProducts() {
        return products;
    }

    public void setProducts(Set<Product> products) {
        this.products = products;
    }

    public Set<Category> getCategories() {
        return categories;
    }

    public void setCategories(Set<Category> categories) {
        this.categories = categories;
    }

}