package com.poly.datn.dto.response;

import com.poly.datn.entity.PromotionUser;
import lombok.Data;

import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.Instant;

/**
 * A DTO for the {@link PromotionUser} entity
 */
@Data
public class PromotionUserResponse implements Serializable {
    private  Integer id;
    @Size(max = 255)
    private  String name_promotion_user;
    private  Double discount_value;
    private  Boolean is_used;
    private  Boolean is_percent;
    private  Instant create_date;
    private  Instant start_date;
    private  Instant expiration_date;
    private Boolean is_deleted;
    @Size(max = 255)
    private  String promotion_code;
    private  Integer promotion_type_id;
    private  Integer user_id;
}