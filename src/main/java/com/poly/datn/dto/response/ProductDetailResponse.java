package com.poly.datn.dto.response;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
public class ProductDetailResponse implements Serializable {
    private Integer id;
    private String sku_name;
    private Integer quantity;
    private Double price;
    private String image;
    private String display_name;
    private List<ProductRatingResponse> rating;
    private Double discount;
    private Double discount_price;
    private Boolean is_percent;
    private Double product_averagePoint;
    private String product_description;
    private List<ProductAttributeResponse> product_productAttributes;
}
