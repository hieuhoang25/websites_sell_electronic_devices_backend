package com.poly.datn.dto.response;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.io.Serializable;
import java.util.Collection;

@Getter
@Setter
public class DecodeJWTResponse implements Serializable {
    private String userName;
    private Collection<SimpleGrantedAuthority> roles;
}
