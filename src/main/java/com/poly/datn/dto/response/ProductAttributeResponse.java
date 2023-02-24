package com.poly.datn.dto.response;

import com.poly.datn.entity.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductAttributeResponse implements Serializable {
    private Integer id;
    private String attribute_name;
    private String attribute_value;
    private Integer product_id;
}
