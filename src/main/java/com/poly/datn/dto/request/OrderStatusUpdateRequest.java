package com.poly.datn.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
public class OrderStatusUpdateRequest {
    @NotNull(message = "{NotNull.id}")
    @Min(1)
    private Integer id;
    @NotBlank(message = "{NotBlank.name}")
    @NotNull
    private String name;
}
