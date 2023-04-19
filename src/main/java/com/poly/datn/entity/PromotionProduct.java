package com.poly.datn.entity;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "promotion_product")
public class PromotionProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "expiration_date")
    private Instant expirationDate;

    @Column(name = "created_date")
    @CreationTimestamp
    private Instant createdDate;

    @Size(max = 255)
    @Column(name = "name")
    private String name;

    // start day
    @Column(name = "updated_date")
    private Instant updatedDate;

    @Column(name = "maximum_price")
    private Double maximumPrice;

    @Column(name = "activate")
    private Boolean activate;
    @Where(clause = "is_delete = false and id IN (SELECT vr.product_id FROM product_variant vr where vr.status = true)")
    @OneToMany(mappedBy = "promotion")
    private Set<Product> products = new LinkedHashSet<>();

    private Double discountAmount;

    private Boolean isPercent;
    @Column(name = "discount")
    private Double discount;
    @Transient
    private Boolean isStart;

    public Boolean getIsStart() {
        return isStart;
    }

    public void setIsStart(Boolean isStart) {
        this.isStart = isStart;
    }

    @PostLoad
    public void checkStartDate() {
        // LocalDate today = LocalDate.now();
        LocalDateTime now = LocalDateTime.now();
        ZoneId zoneId = ZoneId.systemDefault();
        if (expirationDate != null && updatedDate != null) {
            LocalDateTime startTime = updatedDate.atZone(zoneId).toLocalDateTime();
            // LocalDate startDate = updatedDate.atZone(zoneId).toLocalDate();
            // int tDay = today.getDayOfMonth();
            // int start = startDate.getDayOfMonth();
            if (now.isAfter(startTime) || now.isEqual(startTime))
                isStart = true;
            else
                isStart = false;
        } else
            isStart = null;
    }

    public Boolean getPercent() {
        return isPercent;
    }

    public void setPercent(Boolean percent) {
        isPercent = percent;
    }

    public Double getDiscount() {
        return discount;
    }

    public void setDiscount(Double discount) {
        this.discount = discount;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Instant getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(Instant expirationDate) {
        this.expirationDate = expirationDate;
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

    public Double getDiscountAmount() {
        return discountAmount;
    }

    public void setDiscountAmount(Double discountAmount) {
        this.discountAmount = discountAmount;
    }

    public Boolean getIsPercent() {
        return isPercent;
    }

    public void setIsPercent(Boolean isPercent) {
        this.isPercent = isPercent;
    }


}