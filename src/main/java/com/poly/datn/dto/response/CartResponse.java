package com.poly.datn.dto.response;

import java.io.Serializable;
import java.time.Instant;
import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CartResponse implements Serializable {
    
    private Integer id;

    // private UserResponse user;

    private Instant create_date;

    private Float price_sum;

    private List<CartDetailResponse> cartDetails;
}
