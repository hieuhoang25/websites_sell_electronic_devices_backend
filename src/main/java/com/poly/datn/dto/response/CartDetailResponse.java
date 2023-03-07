package com.poly.datn.dto.response;

import java.io.Serializable;
import java.time.Instant;

import com.google.firebase.database.annotations.NotNull;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;


@Data
@NoArgsConstructor
public class CartDetailResponse implements Serializable{
    
    @NotNull
    private Integer id;

    @NotNull
    private Integer productVariant_id;

    @NotNull
    private Integer quantity;


}
