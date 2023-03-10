package com.poly.datn.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Max;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.Instant;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductRequest implements Serializable {
    private Integer id;
    private String product_name;

    private String description;
    private Integer category_id;
    private Boolean is_delete;
    private Integer brand_id;
    private Integer promotion_id;
//    private Instant create_date;

    private String image;
}
