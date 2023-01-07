package com.poly.datn.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
@AllArgsConstructor
public class GenericException extends RuntimeException{
    private final HttpStatus status;
    private final String errorMessage;
    private final String errorDescription;
    private final String correlationId;

}
