package com.poly.datn.dto.request;

import java.io.Serializable;
import java.time.Instant;

import com.google.firebase.database.annotations.NotNull;
import com.poly.datn.dto.response.PromotionUserResponse;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Data
@NoArgsConstructor
public class CheckOutRequest implements Serializable{
    
    @NotNull
    private Integer payment_method_id;

    private Integer promotionUser_id;

    @NotNull
    private String district;

    @NotNull
    private String addressLine;

    @NotNull
    private String province;

    @NotNull
    private String postalId;
}
