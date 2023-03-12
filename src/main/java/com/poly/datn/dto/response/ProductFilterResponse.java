package com.poly.datn.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.Instant;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductFilterResponse implements Serializable {
    private Integer id;
    private String product_name;
    private Integer promotion_id;
    private String image;
    private Double average_point;
    private Instant expiration_date;
    private Double price;
}
