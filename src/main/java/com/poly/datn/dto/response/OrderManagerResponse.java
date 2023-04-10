package com.poly.datn.dto.response;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderManagerResponse implements Serializable {
    private Integer id;
    private String user_fullName;
    private String status_name;
    private String payment_method;
    private String address;
    private String postal_id;
}
