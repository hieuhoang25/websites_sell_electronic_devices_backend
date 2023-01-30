package com.poly.datn.entity;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "product_attribute")
public class ProductAttribute {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Size(max = 50)
    @Column(name = "name_attribute", length = 50)
    private String nameAttribute;

    @OneToMany(mappedBy = "attribute")
    private Set<ProductAttributeValue> productAttributeValues = new LinkedHashSet<>();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNameAttribute() {
        return nameAttribute;
    }

    public void setNameAttribute(String nameAttribute) {
        this.nameAttribute = nameAttribute;
    }

    public Set<ProductAttributeValue> getProductAttributeValues() {
        return productAttributeValues;
    }

    public void setProductAttributeValues(Set<ProductAttributeValue> productAttributeValues) {
        this.productAttributeValues = productAttributeValues;
    }

}