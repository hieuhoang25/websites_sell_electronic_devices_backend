package com.poly.datn.dto.response;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
public class ProductColorResponse implements Serializable {
    private Integer id;
    private String color_name;
}
