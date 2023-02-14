package com.poly.datn.entity;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "product_variant_type")
public class ProductVariantType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Size(max = 50)
    @Column(name = "type_name", length = 50)
    private String typeName;

    @OneToMany(mappedBy = "variantType")
    private Set<ProductAttributeValue> productAttributeValues = new LinkedHashSet<>();

    @OneToMany(mappedBy = "variantType")
    private Set<ProductType> productTypes = new LinkedHashSet<>();

    @OneToMany(mappedBy = "variantType")
    private Set<ProductVariant> productVariants = new LinkedHashSet<>();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public Set<ProductAttributeValue> getProductAttributeValues() {
        return productAttributeValues;
    }

    public void setProductAttributeValues(Set<ProductAttributeValue> productAttributeValues) {
        this.productAttributeValues = productAttributeValues;
    }

    public Set<ProductType> getProductTypes() {
        return productTypes;
    }

    public void setProductTypes(Set<ProductType> productTypes) {
        this.productTypes = productTypes;
    }

    public Set<ProductVariant> getProductVariants() {
        return productVariants;
    }

    public void setProductVariants(Set<ProductVariant> productVariants) {
        this.productVariants = productVariants;
    }

}