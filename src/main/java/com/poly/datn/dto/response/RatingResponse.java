package com.poly.datn.dto.response;

import com.poly.datn.entity.Product;
import com.poly.datn.entity.User;
import lombok.*;

import java.io.Serializable;
import java.time.Instant;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RatingResponse implements Serializable {

    private Integer id;
    private Integer point;
    private Instant created_date;
    private String content;
//    private Product product;
    private Integer user_id;
    private Integer order_detail_id;
}
