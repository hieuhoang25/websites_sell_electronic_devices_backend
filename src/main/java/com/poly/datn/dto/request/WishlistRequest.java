package com.poly.datn.dto.request;

import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class WishlistRequest {

    @NotNull
    private Integer product_id;
    
}
