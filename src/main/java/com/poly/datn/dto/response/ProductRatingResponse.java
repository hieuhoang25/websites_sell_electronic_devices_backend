package com.poly.datn.dto.response;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@NoArgsConstructor
public class ProductRatingResponse {
    private Integer point;
    private String content;
    private Instant created_date;
    private String user_fullName;
}
