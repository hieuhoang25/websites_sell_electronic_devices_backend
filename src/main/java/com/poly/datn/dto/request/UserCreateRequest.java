package com.poly.datn.dto.request;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Data
@NoArgsConstructor
public class UserCreateRequest implements Serializable {
    @Size(max = 255)
    @NotBlank
    private String email;
    @Size(max = 255)
    @NotBlank
    private String full_name;
    @Size(max = 13)
    @NotBlank
    private String phone;
}
