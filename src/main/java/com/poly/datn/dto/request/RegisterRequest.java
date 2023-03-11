package com.poly.datn.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegisterRequest {
    private String email;
    @JsonProperty("full_name")
    private String fullName;
    private String username;
    private String password;
    private String phone;
}
