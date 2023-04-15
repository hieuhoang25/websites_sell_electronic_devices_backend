package com.poly.datn.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "order_detail")
@NoArgsConstructor
@AllArgsConstructor
public class OrderDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    @JsonBackReference
    private Order order;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_variant_id", referencedColumnName = "id")
    private ProductVariant productVariant;

    @Column(name = "create_date")
    @CreationTimestamp
    private LocalDateTime createDate;

    @Column(name = "price_sum")
    private Double priceSum;

    @Column(name = "promotion_value")
    private Double promotionValue;

    @Column(name = "quantity")
    private Integer quantity;

    @OneToOne(mappedBy = "orderDetail")
    private Rating rating;

    private @Transient Double discount;
    private @Transient String variantName;
    private @Transient String variantColor;
    private @Transient Double discountAmount;

    public void setVariantName(String variantName) {
        this.variantName = variantName;
    }

    public void setVariantColor(String variantColor) {
        this.variantColor = variantColor;
    }

    public Double getDiscountAmount() {
        discountAmount = priceSum - promotionValue;
        return discountAmount;
    }

    public void setDiscountAmount(Double discountAmount) {
        this.discountAmount = discountAmount;
    }

    public Double getDiscount() {
        discount = productVariant.getDiscount();
        return discount;
    }

    public String getVariantColor(){
        variantColor = productVariant.getColor().getColorName();
        return variantColor;
    }

    public String getVariantName(){
        variantName = productVariant.getDisplayName();
        return variantName;
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

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public ProductVariant getProductVariant() {
        return productVariant;
    }

    public void setProductVariant(ProductVariant productVariant) {
        this.productVariant = productVariant;
    }

    public LocalDateTime getCreateDate() {
        return createDate;
    }

    public void setCreateDate(LocalDateTime createDate) {
        this.createDate = createDate;
    }

    public Double getPriceSum() {
        return priceSum;
    }

    public void setPriceSum(Double priceSum) {
        this.priceSum = priceSum;
    }

    public Double getPromotionValue() {
        return promotionValue;
    }

    public void setPromotionValue(Double promotionValue) {
        this.promotionValue = promotionValue;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Rating getRating() {
        return rating;
    }

    public void setRating(Rating rating) {
        this.rating = rating;
    }

    public Integer getProductId() {
        return productVariant.getProduct().getId();
    }

}