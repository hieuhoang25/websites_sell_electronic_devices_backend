package com.poly.datn.common;

import com.poly.datn.common.constant.SearchOperation;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SearchCriteria {
    @NotBlank
    private String key;
    private Object value;
    @NotBlank
    private SearchOperation operation;
}
