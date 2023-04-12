package com.poly.datn.dto.request;

import java.io.Serializable;
import java.time.Instant;

import com.google.firebase.database.annotations.NotNull;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor

public class FindAllUserPromoRequest implements Serializable{
    
    @NotNull
    private Double order_total;

    private String code;
}
