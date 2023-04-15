package com.poly.datn.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.poly.datn.common.ValidEmail;
import com.poly.datn.common.ValidPhone;
import lombok.*;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RegisterRequest {
    @ValidEmail
    private String email;
    @JsonProperty("full_name")
    @NotBlank
    private String fullName;
    @NotBlank
    private String username;
    @NotBlank
    private String password;
    @ValidPhone
    private String phone;
}
