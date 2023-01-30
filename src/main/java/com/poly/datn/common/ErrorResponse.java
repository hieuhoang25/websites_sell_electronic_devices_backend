package com.poly.datn.common;

import java.util.List;


public class ErrorResponse {
    private List<ErrorItem> error;
    public void addError(ErrorItem errorItem) {
        error.add(errorItem);
    }
}
