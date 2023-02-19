package com.poly.datn.dto.response;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ProductDetailResponse {
    private Integer id;
    private String sku_name;
    private Integer quantity;
    private Double price;
    private String image;
    private String display_name;
}
