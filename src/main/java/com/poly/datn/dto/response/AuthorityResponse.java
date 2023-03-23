package com.poly.datn.dto.response;

import com.poly.datn.entity.Account;
import com.poly.datn.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthorityResponse implements Serializable {
    private Integer id;
    private Integer role_id;
    private Integer account_id;

    private String role_name;
}
