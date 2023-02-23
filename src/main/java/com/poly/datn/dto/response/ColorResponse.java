package com.poly.datn.dto.response;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ColorResponse implements Serializable {
    private Integer id;
    private String color_name;
    private String color_code;
}
