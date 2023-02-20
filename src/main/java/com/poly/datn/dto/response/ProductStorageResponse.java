package com.poly.datn.dto.response;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
public class ProductStorageResponse implements Serializable {
    private Integer id;
    private String storage_name;
}
