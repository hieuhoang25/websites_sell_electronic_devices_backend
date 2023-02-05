package com.poly.datn.dto.response;

import lombok.Data;

import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.Instant;

@Data
public class UserFindAllResponse implements Serializable {
    private final Integer id;
    @Size(max = 255)
    private final String email;
    @Size(max = 255)
    private final String full_name;
    @Size(max = 13)
    private final String phone;
    private final Instant createDate;
    private final Instant updateDate;
}