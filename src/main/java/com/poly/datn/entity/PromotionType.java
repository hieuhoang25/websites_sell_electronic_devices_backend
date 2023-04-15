package com.poly.datn.entity;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "promotion_type")
public class PromotionType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Size(max = 255)
    @Column(name = "name_promotion_type")
    private String namePromotionType;

    @Column(name = "condition_minimum")
    private Integer conditionMinimum;

    @Column(name = "amount")
    private Double amount;

    @Column(name = "is_limited")
    private Boolean isLimited;

    @Column(name = "descriptions")
    private String descriptions;

    @Column(name = "limited_amount")
    private Integer limitedAmount;

    @OneToMany(mappedBy = "promotionType")
    private Set<PromotionUser> promotionUsers = new LinkedHashSet<>();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNamePromotionType() {
        return namePromotionType;
    }

    public void setNamePromotionType(String namePromotionType) {
        this.namePromotionType = namePromotionType;
    }

    public Integer getConditionMinimum() {
        return conditionMinimum;
    }

    public void setConditionMinimum(Integer conditionMinimum) {
        this.conditionMinimum = conditionMinimum;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Boolean getIsLimited() {
        return isLimited;
    }

    public void setIsLimited(Boolean isLimited) {
        this.isLimited = isLimited;
    }

    public Set<PromotionUser> getPromotionUsers() {
        return promotionUsers;
    }

    public void setPromotionUsers(Set<PromotionUser> promotionUsers) {
        this.promotionUsers = promotionUsers;
    }

    public String getDescriptions() {
        return descriptions;
    }

    public void setDescriptions(String descriptions) {
        this.descriptions = descriptions;
    }

    public Integer getLimitedAmount() {
        return limitedAmount;
    }

    public void setLimitedAmount(Integer limitedAmount) {
        this.limitedAmount = limitedAmount;
    }
}