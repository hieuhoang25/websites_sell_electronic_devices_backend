package com.poly.datn.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RatingProductRequest {
    @Min(1)
    @Max(5)
    @NotNull
    private Integer point;
    @NotNull
    private Integer product_id;
    private String content;
    @NotNull
    private Integer orderDetail_id;
}
