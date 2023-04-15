package com.poly.datn.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PromotionTypeResponse implements Serializable {
    
    private Integer id;

    private String name_promotion_type;

    private Integer condition_minimum;

    private Double amount;

    private Boolean is_limited;

    private String descriptions;

    private Integer limited_amount;

}
