package com.poly.datn.entity;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.time.Instant;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Size(max = 255)
    @Column(name = "email")
    private String email;

    @Size(max = 255)
    @Column(name = "full_name")
    private String fullName;

    @Size(max = 13)
    @Column(name = "phone", length = 13)
    private String phone;
    @CreationTimestamp
    @Column(name = "create_date", updatable = false)
    private Instant createDate;
    @UpdateTimestamp
    @Column(name = "update_date")
    private Instant updateDate;

    @OneToMany(mappedBy = "user")
    private Set<Wishlist> wishlists = new LinkedHashSet<>();

    @OneToOne(mappedBy = "user")
    private Account account;

    @OneToMany(mappedBy = "user")
    private Set<Rating> ratings = new LinkedHashSet<>();

    @OneToMany(mappedBy = "user")
    private Set<Address> addresses = new LinkedHashSet<>();

    @OneToMany(mappedBy = "user")
    private Set<PromotionUser> promotionUsers = new LinkedHashSet<>();

    @OneToMany(mappedBy = "user")
    private Set<Order> orders = new LinkedHashSet<>();

    @OneToOne(mappedBy ="user",cascade = CascadeType.ALL, orphanRemoval = true)
    private Cart carts;

    @OneToMany(mappedBy = "user")
    private List<RefreshToken> refreshTokens;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
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

    public Set<Wishlist> getWishlists() {
        return wishlists;
    }

    public void setWishlists(Set<Wishlist> wishlists) {
        this.wishlists = wishlists;
    }

    public Set<Rating> getRatings() {
        return ratings;
    }

    public void setRatings(Set<Rating> ratings) {
        this.ratings = ratings;
    }

    public Set<Address> getAddresses() {
        return addresses;
    }

    public void setAddresses(Set<Address> addresses) {
        this.addresses = addresses;
    }

    public Set<PromotionUser> getPromotionUsers() {
        return promotionUsers;
    }

    public void setPromotionUsers(Set<PromotionUser> promotionUsers) {
        this.promotionUsers = promotionUsers;
    }

    public Cart getCarts() {
        return carts;
    }

    public void setCarts(Cart carts) {
        if(carts != null) {
            this.carts = carts;
            carts.setUser(this);
        }   
    }

    public boolean existsAccount(){
        return account != null;
    }

    public Set<Order> getOrders() {
        return orders;
    }

    public void setOrders(Set<Order> orders) {
        this.orders = orders;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }
}