package com.poly.datn.entity;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Formula;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

@Entity
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

    @Column(name = "create_date", nullable = false, updatable = false)
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
    private List<ProductAttribute> productAttributes;

    @OneToMany(mappedBy = "product")
    private Set<Rating> ratings = new LinkedHashSet<>();

    @OneToMany(mappedBy = "product")
    private Set<ProductVariant> productVariants = new LinkedHashSet<>();
    private @Transient Double price;
    private @Transient Double averagePoint;
    private @Transient Double discount;
    private @Transient Double discountPrice;
    private @Transient Integer quantity;
//    @Formula("(SELECT COUNT(*)" +
//            " FROM order_detail od join product_variant pr on pr.id = od.product_variant_id" +
//            " join product p on p.id = pr.product_id" +
//            " WHERE p.is_delete = false and p.id = id)")
//    private Integer quantitySold;

//    public Integer getQuantitySold() {
//        return quantitySold;
//    }

//    public void setQuantitySold(Integer quantitySold) {
//        this.quantitySold = quantitySold;
//    }

    public Integer getQuantity() {
        quantity = 0;
        if (!productVariants.isEmpty())
            quantity = productVariants.stream().mapToInt(ProductVariant::getQuantity).sum();
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public void setDiscount(Double discount) {
        this.discount = discount;
    }

    public Double getDiscount() {
        discount = 0.0d;
        LocalDateTime today = LocalDateTime.now();
        ZoneId zoneId = ZoneId.systemDefault();
        if (promotion != null && promotion.getActivate()) {
            Boolean hasExpireDate = promotion.getExpirationDate() != null;
            Boolean expiredDate =
                    hasExpireDate && today.isBefore(promotion.getExpirationDate().atZone(zoneId).toLocalDateTime());
            if (promotion.getUpdatedDate() != null && expiredDate) {
                LocalDateTime startDate = promotion.getUpdatedDate().atZone(zoneId).toLocalDateTime();
                if (today.isAfter(startDate) || today.equals(startDate))
                    discount = promotion.getDiscountAmount();
            }
        }
        return discount;
    }

//    public Boolean getIsPercent(){
//        return promotion.getIsPercent();
//    }

    public Double getDiscountPrice() {
        discountPrice = 0.0d;
        if (discount != 0) {
            discountPrice = getPrice() - getPrice() * (getDiscount() / 100);
        }
        return discountPrice;
    }

    public void setDiscountPrice(Double discountPrice) {
        this.discountPrice = discountPrice;
    }

    public Double getPrice() {
        price = 0.0;
        if (!productVariants.isEmpty())
            price = productVariants.stream().mapToDouble(ProductVariant::getPrice).min().getAsDouble();
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getAveragePoint() {
        int size = ratings.size();
        averagePoint = 0.0;
        if (size != 0)
            averagePoint = ratings.stream().mapToDouble(Rating::getPoint).sum() / size;
        return averagePoint;
    }

    public void setAveragePoint(Double averagePoint) {
        this.averagePoint = averagePoint;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Boolean getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Boolean isDelete) {
        this.isDelete = isDelete;
    }

    public Brand getBrand() {
        return brand;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }

    public PromotionProduct getPromotion() {
        return promotion;
    }

    public void setPromotion(PromotionProduct promotion) {
        this.promotion = promotion;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Set<Wishlist> getWishlists() {
        return wishlists;
    }

    public Boolean getDelete() {
        return isDelete;
    }

    public void setDelete(Boolean delete) {
        isDelete = delete;
    }

    public void setWishlists(Set<Wishlist> wishlists) {
        this.wishlists = wishlists;
    }

    public List<ProductAttribute> getProductAttributes() {
        return productAttributes;
    }

    public void setProductAttributes(List<ProductAttribute> productAttributes) {
        this.productAttributes = productAttributes;
    }

    public Set<Rating> getRatings() {
        return ratings;
    }

    public void setRatings(Set<Rating> ratings) {
        this.ratings = ratings;
    }

    public Set<ProductVariant> getProductVariants() {
        return productVariants;
    }

    public void setProductVariants(Set<ProductVariant> productVariants) {
        this.productVariants = productVariants;
    }

    @PostPersist
    private void postCreate() {
        this.image = "product-" + this.id + ".png";
    }
}
