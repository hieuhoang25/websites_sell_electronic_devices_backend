package com.poly.datn.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderTrackingResponse {
    private Integer id;
    private String status_name;
    private Integer status_id;
    private String status_title;
    Set<OrderDetailResponse> orderDetails;
}
