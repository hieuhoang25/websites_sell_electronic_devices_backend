package com.poly.datn.dto.response;

import java.io.Serializable;
import java.time.Instant;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;


@Data
@NoArgsConstructor
public class CartDetailResponse implements Serializable{
    
    private Integer id;

    private Integer quantity;

    private Instant createDate;

    private Integer productVariant_id;

    private String productVariant_product_name;
}
