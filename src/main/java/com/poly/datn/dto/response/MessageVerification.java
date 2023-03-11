package com.poly.datn.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MessageVerification {
    private String message;
    private Integer status;
}
