package com.poly.datn.dto.response;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.Instant;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccountResponse implements Serializable {
    private Integer id;
    private String username;
//    private String password;
    private Instant createDate;
    private Instant updateDate;
    private Boolean active;
    private Integer user_id;

    private UserByAccountResponse user;
    private Instant lastAccess;
    private Set<AuthorityResponse> authorities;


}
