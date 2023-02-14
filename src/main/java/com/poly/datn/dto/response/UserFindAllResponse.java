package com.poly.datn.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.Instant;

@Data
@NoArgsConstructor
public class UserFindAllResponse implements Serializable {
    private Integer id;
    @Size(max = 255)
    private String email;
    @Size(max = 255)
    private String full_name;
    @Size(max = 13)
    private String phone;
    private Instant create_date;
    private Instant update_date;
}