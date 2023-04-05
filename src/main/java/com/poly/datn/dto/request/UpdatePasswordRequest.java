package com.poly.datn.dto.request;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;


@Data
public class UpdatePasswordRequest {

    @Length(min = 3)
    @NotNull
    private String old_password;
    @Length(min = 3)
    @NotNull
    private String new_password;
    @Length(min = 3)
    @NotNull
    private String confirm_password;
}
