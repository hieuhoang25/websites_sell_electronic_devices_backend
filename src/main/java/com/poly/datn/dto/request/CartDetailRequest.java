package com.poly.datn.dto.request;

import java.io.Serializable;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import com.google.auto.value.AutoValue.Builder;
import com.poly.datn.dto.response.CartDetailResponse;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class CartDetailRequest  implements Serializable {
    
    
    private Integer id;

    @NotNull
    private Integer cart_id;

    @Min(value= 1, message="quantity per item must be more than 1")
    @Max(value= 5, message="quantity per item must can't more than 1")
    @NotNull
    protected Integer quantity;

    protected Integer product_variant_id;

   
    // public static CartDetailRequest getRequestFromGuestCartDetail(CartDetailResponse response, Integer cartId) {

    //     Integer id = response.getId();
    //     Integer qty = response.getQuantity();
    //     Integer variantId = response.getProductVariant().getId();
    //     return new CartDetailRequest(id, cartId,qty,variantId);
    // }
    
}
