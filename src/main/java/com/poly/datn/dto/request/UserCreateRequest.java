package com.poly.datn.dto.request;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Getter
@Setter
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

    public void UserCreateRequest(String email,String full_name){
        this.email = email;
        this.full_name = full_name;
    }
}
