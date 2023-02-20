package com.poly.datn.dto.request;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
public class ProductDetailRequest implements Serializable {
    @NotNull(message = "{NotNull.colorId}")
    @Min(1)
    private Integer colorId;
    @NotNull(message = "{NotNull.productId}")
    @Min(1)
    private Integer productId;
    @NotNull(message = "{NotNull.storageId}")
    @Min(1)
    private Integer storageId;
}
