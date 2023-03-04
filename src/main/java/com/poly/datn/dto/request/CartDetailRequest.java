package com.poly.datn.dto.request;

import java.io.Serializable;

import lombok.Data;

@Data
public class CartDetailRequest implements Serializable{
    
    private Integer id;

    private Integer quantity;

    private Integer product_variant_id;
}
