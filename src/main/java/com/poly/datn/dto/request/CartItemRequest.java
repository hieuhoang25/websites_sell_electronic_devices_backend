package com.poly.datn.dto.request;

import java.io.Serializable;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


import lombok.Data;
import lombok.NoArgsConstructor;

// product_variant, quantity
@Data
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = false)
public class CartItemRequest implements Serializable {

    
    @NotNull
    private Integer cart_id;

    @NotNull
    private Integer product_variant_id;

    @Min(value= 1, message="quantity per item must be more than 1")
    @Max(value= 5, message="quantity per item must can't more than 1")
    @NotNull
    private Integer quantity;

    public CartDetailRequest toCartDetailRequest(Integer detailId) {
        CartDetailRequest dr = new CartDetailRequest(detailId, this.cart_id,this.quantity, this.product_variant_id);
        return dr;
    }
}
