package com.poly.datn.dto.response;

import lombok.*;

import java.io.Serializable;
import java.time.Instant;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrdersUserResponse implements Serializable {
    private Integer id;
    private String user_email;
    private String user_fullName;
    private String address;
    private Instant created_date;
    private Boolean is_pay;
    private String payment_method;
    private String status_name;
    private Double sum;

    private Double total;
    private Set<OrderDetailResponse> orderDetails;
}


