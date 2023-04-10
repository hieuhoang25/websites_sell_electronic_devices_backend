package com.poly.datn.dto.response;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.Instant;

@Data
@NoArgsConstructor
public class ProductRatingResponse implements Serializable {
    private Integer point;
    private String content;
    private Instant created_date;
    private String user_fullName;
}
