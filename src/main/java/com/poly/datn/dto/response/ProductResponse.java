package com.poly.datn.dto.response;

import com.poly.datn.entity.Rating;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.Instant;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductResponse implements Serializable {
    private Integer id;

    private String product_name;

    private String productName;

    private String description;
    private Instant create_date;
    private Instant update_date;

    private Integer category_id;

    private Boolean is_delete;

    private Integer brand_id;
    private Integer promotion_id;

    private String image;


    private Set<RatingResponse> ratings ;




}
