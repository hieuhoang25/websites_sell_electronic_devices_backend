package com.poly.datn.dto.request;

import com.poly.datn.entity.PromotionType;
import com.poly.datn.entity.PromotionUser;
import com.poly.datn.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.Instant;

/**
 * A DTO for the {@link PromotionUser} entity
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PromotionUserRequest implements Serializable {
    private Integer id;
    @Size(max = 255)
    private  String namePromotionUser;
    private  Integer discountValue;
    private  Integer isUsed;
    private  Instant expirationDate;
    @Size(max = 255)
    private  String promotionCode;
    private  Integer promotion_type_id;
    private  Integer user_id;
}