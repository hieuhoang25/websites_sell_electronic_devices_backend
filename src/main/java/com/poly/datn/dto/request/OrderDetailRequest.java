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
    Integer id;
}
