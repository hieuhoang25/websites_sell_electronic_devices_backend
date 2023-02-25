package com.poly.datn.dto.response;

import com.poly.datn.entity.Product;
import com.poly.datn.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.Instant;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RatingResponse implements Serializable {

    private Integer id;
    private Integer point;
    private Instant createdDate;
    private String content;
//    private Product product;
    private Integer user_id;
    private Integer order_detail_id;
}
