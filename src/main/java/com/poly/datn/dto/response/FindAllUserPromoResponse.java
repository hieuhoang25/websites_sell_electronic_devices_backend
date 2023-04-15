package com.poly.datn.dto.response;

import java.io.Serializable;
import java.time.Instant;

import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FindAllUserPromoResponse implements Serializable {

    private Integer id;
    private Instant create_date;
    private String name_promotion_user;

    private String promotion_code;
    private Double discount_value;
    private Boolean is_used;
    private Boolean is_percent;
    private Instant start_date;
    private Instant expiration_date;
    private Boolean  is_deleted;

    private Integer promotion_type_id;
    private Integer promotion_type_conditionMinimum;

    private String promotion_type_namePromotionType;

}
