package com.poly.datn.dto.request;

import com.poly.datn.common.ValidEmail;
import com.poly.datn.common.ValidPhone;
import com.poly.datn.dto.response.AddressResponse;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Set;

@Data
public class UserInfoRequest {
    @NotNull(message = "Vui lòng nhập họ và tên")
    @NotBlank(message = "Họ và tên không được để trống")
    private String full_name;

    @ValidEmail
    @NotBlank
    private String email;

    @ValidPhone
    @NotBlank
    private String phone;
}
