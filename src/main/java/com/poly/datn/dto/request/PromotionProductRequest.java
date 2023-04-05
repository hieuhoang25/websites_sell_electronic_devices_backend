package com.poly.datn.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.Instant;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PromotionProductRequest implements Serializable {

    private Integer id;

    private Instant expiration_date;

    private String name;

    private Double maximum_price;

    private Boolean activate;

    private Double discount_amount;

    private Boolean is_percent;

}
