package com.poly.datn.dto.request;


import java.io.Serializable;

import javax.validation.constraints.NotNull;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder(setterPrefix = "with")
@NoArgsConstructor
@AllArgsConstructor
public class UserAddressRequest implements Serializable{


    @NotNull
    private Integer id;
    @NotNull
    private String wards;
    @NotNull
    private String district;
    @NotNull
    private String address_line;
    @NotNull
    private String province;
    @NotNull
    private String postal_id;

    @Setter(value = AccessLevel.NONE)
    @NotNull
    private Boolean is_default;


    public void setIs_default(Boolean is_default) {
        System.out.println("call set is_default: " + is_default);
        if(this.is_default == null)  this.is_default = false;
        if(is_default == null ) this.is_default = false;
        else this.is_default = is_default;
    }
}
