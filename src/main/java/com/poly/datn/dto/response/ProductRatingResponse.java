package com.poly.datn.dto.response;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ProductRatingResponse {
    private Integer point;
    private String content;
}
