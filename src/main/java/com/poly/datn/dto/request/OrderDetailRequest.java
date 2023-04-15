package com.poly.datn.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
public class OrderDetailRequest {
    @NotNull
    private Integer id;
    @NotNull
    private Integer productVariant_id;
    @NotNull
    private Integer quantity;
}
