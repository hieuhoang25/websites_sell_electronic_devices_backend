package com.poly.datn.entity;

import java.time.Instant;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Formula;

@Entity
@Table(name = "cart_detail")
public class CartDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cart_id")
    private Cart cart;

    @Column(name = "quantity")
    private Integer quantity;

    @Column(name = "create_date")
    @CreationTimestamp
    private Instant createDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_variant_id")
    private ProductVariant productVariant;

    // @Formula("(select sum(d.quantity * v.price) from cart_detail d join cart c on d.cart_id = c.id join product_variant v on v.id = d.product_variant_id where d.cart_id = id )")

    @Formula("(select sum(quantity * v.price) from cart_detail d join product_variant v on v.id = d.product_variant_id  where v.id = product_variant_id)")
    private Double price_detail;


    public Double getPrice_Detail() {
        return price_detail == null ? 0.0 : price_detail;
    }
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Instant getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Instant createDate) {
        this.createDate = createDate;
    }

    public ProductVariant getProductVariant() {
        return productVariant;
    }

    public void setProductVariant(ProductVariant productVariant) {
        this.productVariant = productVariant;
    }

}