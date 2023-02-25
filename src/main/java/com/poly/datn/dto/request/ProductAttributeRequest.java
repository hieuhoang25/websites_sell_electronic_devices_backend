package com.poly.datn.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductAttributeRequest implements Serializable {
    private Integer id;
    private String attribute_name;
    private String attribute_value;
    private Integer product_id;
}
