package com.poly.datn.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
public class LoginRequest {
    @NotBlank
    @NotNull
    private String userName;
    @NotBlank
    @NotNull
    private String password;


}
