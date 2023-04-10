package com.poly.datn.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductOfBrandResponse implements Serializable {
    private Integer id;

    private String product_name;

    private String productName;

    private String description;

    private String image;

}
