package com.poly.datn.common;

import lombok.Data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
public class ErrorResponse {
    private Map<ErrorItem,String> error = new HashMap<>();
    public void addError(ErrorItem errorItem,String key) {
        error.put(errorItem,key);
    }
}
