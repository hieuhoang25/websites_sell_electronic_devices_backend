package com.poly.datn.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PromotionTypeRequest implements Serializable {
    private Integer id;

    private String name_promotion_type;

    private Integer condition_minimum;

    private Double amount;

    private Boolean is_limited;

}
