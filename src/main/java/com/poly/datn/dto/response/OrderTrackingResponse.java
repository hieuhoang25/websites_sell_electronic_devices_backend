package com.poly.datn.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.Instant;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderTrackingResponse implements Serializable {
    private Integer id;
    private String status_name;
    private Integer status_id;
    private String status_title;
    private Instant created_date;
    private String address;
    private Double total;
    private String promotion_name;
    private Boolean promotion_isPercent;
    private Double discount;
    private Double discount_amount;
    List<OrderDetailResponse> orderDetails;
}
