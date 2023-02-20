package com.poly.datn.common;

import lombok.Data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
public class ErrorResponse {
    private List<ErrorItem> error = new ArrayList<>();
    public void addError(ErrorItem errorItem) {
        error.add(errorItem);
    }
}
