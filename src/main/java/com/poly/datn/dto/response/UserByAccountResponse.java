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
public class UserByAccountResponse implements Serializable {

    private Integer id;
    private String email;
    private String fullName;
    private String phone;
    private Instant createDate;
    private Instant updateDate;
//    private Set<RatingResponse> ratings ;
    private Set<AddressResponse> addresses ;
}
