package com.poly.datn.dto.response;

import com.poly.datn.entity.Authority;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.LinkedHashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RoleResponse implements Serializable {
    private Integer id;
    private String role_name;
}
