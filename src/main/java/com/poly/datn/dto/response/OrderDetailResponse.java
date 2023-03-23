package com.poly.datn.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderDetailResponse {
    private Double price_sum;
    private Double productVariant_price;
    private String productVariant_image;
    private String productVariant_displayName;
    private String productVariant_color_name;
    private Integer quantity;
    private Double promotion_value;
}
