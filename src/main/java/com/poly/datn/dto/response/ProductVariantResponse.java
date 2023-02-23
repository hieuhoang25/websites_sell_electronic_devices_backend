package com.poly.datn.dto.response;

import com.poly.datn.entity.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.LinkedHashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductVariantResponse implements Serializable {
    private Integer id;
    private String sku_name;
    private Integer quantity;
    private Double price;
    private Boolean status;
    private Integer product_id;
    private String image;
    private String display_name;

    private Integer color_id;
    private Integer storage_id;
//    private Set<ColorResponse> color;
//    private Set<StorageResponse> storage;
}
