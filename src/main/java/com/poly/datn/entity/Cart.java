package com.poly.datn.entity;

import java.time.Instant;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedStoredProcedureQueries;
import javax.persistence.NamedStoredProcedureQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureParameter;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Formula;

import lombok.AllArgsConstructor;

@Entity
@Table(name = "cart")
@NamedStoredProcedureQueries({
        @NamedStoredProcedureQuery(name = "updateCartPriceSum", procedureName = "sp_sumTotalInCart", parameters = {
                @StoredProcedureParameter(mode = ParameterMode.IN, name = "cartId", type = Integer.class),
                @StoredProcedureParameter(mode = ParameterMode.OUT, name = "updated", type = Integer.class),
        }),
        @NamedStoredProcedureQuery(name = "updatedCartQuantityByInventory", procedureName = "sp_updateCartByInventory", parameters = {
            @StoredProcedureParameter(mode = ParameterMode.IN, name = "cartId", type = Integer.class),
            @StoredProcedureParameter(mode = ParameterMode.OUT, name = "ischanged", type = Boolean.class),
        }),
        @NamedStoredProcedureQuery(name = "updatedCartByVariantStatus", procedureName = "sp_updateCartByVariantStatus", parameters = {
            @StoredProcedureParameter(mode = ParameterMode.IN, name = "cartId", type = Integer.class),
            @StoredProcedureParameter(mode = ParameterMode.OUT, name = "isRemoved", type = Boolean.class),
        }),
})
@AllArgsConstructor
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @Column(name = "create_date")
    @CreationTimestamp
    private Instant createDate;

    @Column(name = "price_sum")
    private Double priceSum = 0.0;

  
    @OneToMany(mappedBy = "cart", fetch = FetchType.LAZY, cascade = {CascadeType.ALL})
    private Set<CartDetail> cartDetails = new LinkedHashSet<>();

    public Cart() {
        
    }
    public Cart(Integer id) {
        this.id = id;
        this.priceSum = 0.0;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        if(user != null) {
            // user.setCarts(this);
            this.user = user;
        }
        
    }

    public Instant getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Instant createDate) {
        this.createDate = createDate;
    }

    public Double getPriceSum() {
        return priceSum;
    }

    public void setPriceSum(Double priceSum) {
        this.priceSum = priceSum;
    }

    public Set<CartDetail> getCartDetails() {
        return cartDetails;
    }

    public void setCartDetails(Set<CartDetail> cartDetails) {
        if(cartDetails != null) {
            this.cartDetails = cartDetails;
            this.cartDetails.forEach(cd -> cd.setCart(this));
        }
     
    }

}