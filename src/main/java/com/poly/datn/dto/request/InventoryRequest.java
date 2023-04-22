package com.poly.datn.dto.request;

import java.io.Serializable;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class InventoryRequest implements Serializable {
    
    @NotNull
    private Integer product_variant_id;

    @NotNull
    @Min(0)
    private Integer request_quantity;
}
