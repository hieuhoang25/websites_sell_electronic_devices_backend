package com.poly.datn.entity;

import java.time.Instant;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.*;
import javax.validation.constraints.Size;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Formula;

import com.google.firebase.database.annotations.NotNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "orders")
@NoArgsConstructor
@AllArgsConstructor
@Builder(setterPrefix = "with")
public class Order {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    @NotNull
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    @NotNull
    private User user;

    @Column(name = "created_date")
    @CreationTimestamp
    private Instant createdDate;

    @Column(name = "is_pay")
    private Boolean isPay;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "payment_id")
    @NotNull
    private PaymentMethod payment;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "status")
    @NotNull
    private OrderStatus status;

    @Column(name = "is_cancelled")
    private Boolean isCancelled;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "promotion_id")
    private PromotionUser promotion;

    @Size(max = 50)
    @Column(name = "district", length = 50)
    private String district;

    @Size(max = 50)
    @Column(name = "address_line", length = 50)
    private String addressLine;

    @Size(max = 50)
    @Column(name = "province", length = 50)
    private String province;

    @Size(max = 50)
    @Column(name = "postal_id", length = 50)
    private String postalId;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<OrderDetail> orderDetails = new LinkedHashSet<>();

    private @Transient Double total;
    private @Transient String promotionName;
    private @Transient Boolean isPercent;
    private @Transient Double discount;
    private @Transient Double discountAmount;

    public Double getDiscountAmount() {
        discountAmount = 0.0d;
        if (promotion != null && getIsPercent() != null) {
            if (getIsPercent())
                discountAmount = getTotal() * (promotion.getDiscountValue() / 100);
            else
                discountAmount = promotion.getDiscountValue();
        }
        return discountAmount;
    }

    public void setDiscountAmount(Double discountAmount) {
        this.discountAmount = discountAmount;
    }

    public String getPromotionName() {
        promotionName = "";
        if (promotion != null)
            promotionName = promotion.getNamePromotionUser();
        return promotionName;
    }

    public void setPromotionName(String promotionName) {
        this.promotionName = promotionName;
    }

    public Boolean getIsPercent() {
        isPercent = null;
        if (promotion != null)
            isPercent = promotion.getIsPercent();
        return isPercent;
    }

    public void setIsPercent(Boolean percent) {
        isPercent = percent;
    }

    public Double getDiscount() {
        discount = 0.0d;
        if(promotion != null)
            discount = promotion.getDiscountValue();
        return discount;
    }

    public void setDiscount(Double discount) {
        this.discount = discount;
    }

    public Boolean getPay() {
        return isPay;
    }

    public void setPay(Boolean pay) {
        isPay = pay;
    }

    public Boolean getCancelled() {
        return isCancelled;
    }

    public void setCancelled(Boolean cancelled) {
        isCancelled = cancelled;
    }

    public Double getTotal() {
        total = 0.0d;
        if (!orderDetails.isEmpty())
            total = orderDetails.stream().mapToDouble(OrderDetail::getDiscountAmount).sum();
        if (promotion != null && getIsPercent() != null) {
            if (getIsPercent())
                total = total - total * (promotion.getDiscountValue() / 100);
            else
                total = total - promotion.getDiscountValue();
        }
        return total;
    }


    public void setTotal(Double priceSum) {
        this.total = priceSum;
    }

    public String getAddress() {
        return addressLine + " " + district + " " + province;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Instant getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Instant createdDate) {
        this.createdDate = createdDate;
    }

    public Boolean getIsPay() {
        return isPay;
    }

    public void setIsPay(Boolean isPay) {
        this.isPay = isPay;
    }

    public PaymentMethod getPayment() {
        return payment;
    }

    public void setPayment(PaymentMethod payment) {
        this.payment = payment;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public Boolean getIsCancelled() {
        return isCancelled;
    }

    public void setIsCancelled(Boolean isCancelled) {
        this.isCancelled = isCancelled;
    }

    public PromotionUser getPromotion() {
        return promotion;
    }

    public void setPromotion(PromotionUser promotion) {
        this.promotion = promotion;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getAddressLine() {
        return addressLine;
    }

    public void setAddressLine(String addressLine) {
        this.addressLine = addressLine;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getPostalId() {
        return postalId;
    }

    public void setPostalId(String postalId) {
        this.postalId = postalId;
    }

    public Set<OrderDetail> getOrderDetails() {
        return orderDetails;
    }

    public void setOrderDetails(Set<OrderDetail> orderDetails) {
        if (!(orderDetails == null)) {
            this.orderDetails = new LinkedHashSet<>(orderDetails);
            orderDetails.stream().forEach(detail -> detail.setOrder(this));
        }
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}