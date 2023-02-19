package com.poly.datn.dto.request;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
public class ProductDetailRequest {
    @NotNull
    @Min(1)
    private Integer colorId;
    @NotNull
    @Min(1)
    private Integer productId;
    @NotNull
    @Min(1)
    private Integer storageId;
}
