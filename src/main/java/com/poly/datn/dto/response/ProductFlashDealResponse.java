package com.poly.datn.dto.response;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductFlashDealResponse implements Serializable {
    private Integer id;
    private String product_name;
    private Integer promotion_id;
    private Double discount;
    private String image;
    private Double average_point;
    private Double price;
    private Double discount_price;
}


    
