package com.poly.datn.dto.response;

import com.poly.datn.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddressResponse implements Serializable {
    private Integer id;
    private String wards;
    private String district;
    private String address_line;
    private String province;
    private String postal_id;
    private Boolean is_default;
}
