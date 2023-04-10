package com.poly.datn.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserLoggedResponse implements Serializable {
    private Integer id;
    private String email;
    private String full_name;
    private String phone;
    private Set<AddressResponse> addresses;
}
