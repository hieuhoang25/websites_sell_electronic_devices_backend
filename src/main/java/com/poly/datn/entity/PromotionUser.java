package com.poly.datn.entity;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.time.Instant;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "promotion_user")
public class PromotionUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Size(max = 255)
    @Column(name = "name_promotion_user")
    private String namePromotionUser;

    @Column(name = "discount_value")
    private Integer discountValue;

    @Column(name = "is_used")
    private Integer isUsed;

    @Column(name = "create_date")
    @CreationTimestamp
    private Instant createDate;

    @Column(name = "update_date")
    @UpdateTimestamp
    private Instant updateDate;

    @Column(name = "expiration_date")
    private Instant expirationDate;

    @Size(max = 255)
    @Column(name = "promotion_code")
    private String promotionCode;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "promotion_type")
    private PromotionType promotionType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "promotion")
    private Set<Order> orders = new LinkedHashSet<>();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNamePromotionUser() {
        return namePromotionUser;
    }

    public void setNamePromotionUser(String namePromotionUser) {
        this.namePromotionUser = namePromotionUser;
    }

    public Integer getDiscountValue() {
        return discountValue;
    }

    public void setDiscountValue(Integer discountValue) {
        this.discountValue = discountValue;
    }

    public Integer getIsUsed() {
        return isUsed;
    }

    public void setIsUsed(Integer isUsed) {
        this.isUsed = isUsed;
    }

    public Instant getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Instant createDate) {
        this.createDate = createDate;
    }

    public Instant getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Instant updateDate) {
        this.updateDate = updateDate;
    }

    public Instant getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(Instant expirationDate) {
        this.expirationDate = expirationDate;
    }

    public String getPromotionCode() {
        return promotionCode;
    }

    public void setPromotionCode(String promotionCode) {
        this.promotionCode = promotionCode;
    }

    public PromotionType getPromotionType() {
        return promotionType;
    }

    public void setPromotionType(PromotionType promotionType) {
        this.promotionType = promotionType;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Set<Order> getOrders() {
        return orders;
    }

    public void setOrders(Set<Order> orders) {
        this.orders = orders;
    }

}