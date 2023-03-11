package com.poly.datn.dto.response;

import java.io.Serializable;
import java.time.Instant;
import java.util.Set;

import lombok.Data;

@Data
public class OrderResponse implements Serializable{
    private Integer id;

    private Integer user_id;

    private Instant createdDate;

    private Boolean isPay;

    private String payment_method;

    private String status_name;

    private Boolean isCancelled;

    private PromotionUserResponse promotion;

    private String district;

    private String addressLine;

    private String province;

    private String postalId;

    private Set<OrderDetailResponse> orderDetails;
}
