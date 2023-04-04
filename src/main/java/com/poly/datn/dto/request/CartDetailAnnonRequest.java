package com.poly.datn.dto.request;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CartDetailAnnonRequest {

    private Integer variant_id;

    @Min(value= 1, message="quantity per item must be more than 1")
    @Max(value= 5, message="quantity per item must can't more than 1")
    @NotNull
    private Integer quantity;
    
}
