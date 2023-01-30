package com.poly.datn.entity;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "product_type")
public class ProductType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Size(max = 255)
    @Column(name = "name")
    private String name;

    @Column(name = "mutil_variation")
    private Boolean mutilVariation;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "variant_type_id")
    private ProductVariantType variantType;

    @OneToMany(mappedBy = "type")
    private Set<Product> products = new LinkedHashSet<>();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getMutilVariation() {
        return mutilVariation;
    }

    public void setMutilVariation(Boolean mutilVariation) {
        this.mutilVariation = mutilVariation;
    }

    public ProductVariantType getVariantType() {
        return variantType;
    }

    public void setVariantType(ProductVariantType variantType) {
        this.variantType = variantType;
    }

    public Set<Product> getProducts() {
        return products;
    }

    public void setProducts(Set<Product> products) {
        this.products = products;
    }

}