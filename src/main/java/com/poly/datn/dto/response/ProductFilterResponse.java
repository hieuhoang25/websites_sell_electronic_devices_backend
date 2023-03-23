package com.poly.datn.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductFilterResponse implements Serializable {
    private Integer id;
    private String product_name;
    private Integer promotion_id;
    private String image;
    private Double average_point;
    private Double price;

}
