package com.poly.datn.dto.request;

import java.io.Serializable;

import lombok.Data;
import lombok.NoArgsConstructor;

// product_variant, quantity
@Data
@NoArgsConstructor
public class CartItemRequest implements Serializable{

    private Integer quantity;

    private Integer product_variant_id;
    
}
