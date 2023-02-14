package com.poly.datn.entity;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "product_attribute_value")
public class ProductAttributeValue {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Size(max = 100)
    @Column(name = "attribute_value", length = 100)
    private String attributeValue;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "attribute_id")
    private ProductAttribute attribute;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "variant_type_id")
    private ProductVariantType variantType;

    @OneToMany(mappedBy = "attributeValue")
    private Set<ProductVariantOption> productVariantOptions = new LinkedHashSet<>();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAttributeValue() {
        return attributeValue;
    }

    public void setAttributeValue(String attributeValue) {
        this.attributeValue = attributeValue;
    }

    public ProductAttribute getAttribute() {
        return attribute;
    }

    public void setAttribute(ProductAttribute attribute) {
        this.attribute = attribute;
    }

    public ProductVariantType getVariantType() {
        return variantType;
    }

    public void setVariantType(ProductVariantType variantType) {
        this.variantType = variantType;
    }

    public Set<ProductVariantOption> getProductVariantOptions() {
        return productVariantOptions;
    }

    public void setProductVariantOptions(Set<ProductVariantOption> productVariantOptions) {
        this.productVariantOptions = productVariantOptions;
    }

}