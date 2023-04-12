package com.poly.datn.dto.response;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.Instant;

@Getter
@Setter
@NoArgsConstructor
public class ProductRatingResponse implements Serializable {
    private Integer point;
    private String content;
    private Instant created_date;
    private String user_fullName;
    private String variant_name;
    private String variant_color;
}
