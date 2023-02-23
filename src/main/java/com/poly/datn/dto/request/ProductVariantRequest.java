package com.poly.datn.dto.request;

import com.poly.datn.dto.response.ColorResponse;
import com.poly.datn.dto.response.ProductResponse;
import com.poly.datn.dto.response.RatingResponse;
import com.poly.datn.dto.response.StorageResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.Instant;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductVariantRequest  implements Serializable {
    private Integer id;
    private String sku_name;
    private Integer quantity;
    private Double price;
    private Boolean status;
    private Integer product_id ;
    private String image;
    private String display_name;
    private Integer color_id;
    private Integer storage_id;

}
