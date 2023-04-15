package com.poly.datn.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "product_variant")
// sp_checkCurrentInvetory
@NamedStoredProcedureQueries({
    @NamedStoredProcedureQuery(name = "checkInventoryById", procedureName = "sp_checkCurrentInvetory", parameters = {
            @StoredProcedureParameter(mode = ParameterMode.IN, name = "variant_id", type = Integer.class),
            @StoredProcedureParameter(mode = ParameterMode.IN, name = "reQty", type = Integer.class),
            @StoredProcedureParameter(mode = ParameterMode.OUT, name = "checked", type = Integer.class)
    })
})
public class ProductVariant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Size(max = 255)
    @Column(name = "sku_name")
    private String skuName;

    @Column(name = "quantity")
    private Integer quantity;

    @Column(name = "price")
    private Double price;

    @Column(name = "status")
    private Boolean status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product product;

    @Size(max = 255)
    @Column(name = "image")
    private String image;

    @Size(max = 255)
    @Column(name = "display_name")
    private String displayName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "color_id")
    private Color color;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "storage_id")
    private Storage storage;

    @OneToMany(mappedBy = "productVariant")
    private Set<CartDetail> cartDetails = new LinkedHashSet<>();

    @OneToMany(mappedBy = "productVariant")
    private Set<OrderDetail> orderDetails = new LinkedHashSet<>();
    private @Transient Double discountPrice;

    public Double getDiscount() {
        return product.getDiscount();
    }

    //    public Boolean isPercent(){
//        return product.getIsPercent();
//    }
    public Double getDiscountPrice() {
        discountPrice = 0.0d;
        if (getDiscount() != 0)
            discountPrice = price - price * (getDiscount() / 100);
        return discountPrice;
    }

    @PostPersist
    private void afterCreate() {
        this.status = true;
        this.image = "productVariant-" + this.id;
    }

}