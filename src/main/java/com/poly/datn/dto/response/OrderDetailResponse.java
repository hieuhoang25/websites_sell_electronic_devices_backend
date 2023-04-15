package com.poly.datn.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderDetailResponse implements Serializable {
    private Integer id;
    private Integer product_id;
    private Double price_sum;
    private Integer productVariant_id;
    private Double productVariant_price;
    private String productVariant_image;
    private String productVariant_displayName;
    private String productVariant_color_name;
    private Integer quantity;
    private Double promotion_value;
    private Double discount;
    private Double discount_amount;
}
