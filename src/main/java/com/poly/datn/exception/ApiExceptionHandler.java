package com.poly.datn.exception;

import com.poly.datn.common.ErrorItem;
import com.poly.datn.common.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

@RestControllerAdvice
public class ApiExceptionHandler{
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ErrorResponse> handle(ConstraintViolationException e) {
        ErrorResponse errors = new ErrorResponse();
        for (ConstraintViolation violation : e.getConstraintViolations()) {
            ErrorItem error = new ErrorItem();
            error.setCode(violation.getMessageTemplate());
            error.setMessage(violation.getMessage());
            errors.addError(error,"");
        }
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleMethodArgumentNotValid (MethodArgumentNotValidException e){
        ErrorResponse errors = new ErrorResponse();
        e.getBindingResult().getAllErrors().forEach((err) ->{
            ErrorItem error = new ErrorItem();
            error.setCode(error.getCode());
            error.setMessage(error.getMessage());
            String fieldName = ((FieldError) err).getField();
            errors.addError(error,fieldName);
        });
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }


//    @SuppressWarnings("rawtypes")
//    @ExceptionHandler(ResourceNotFoundException.class)
//    public ResponseEntity<ErrorItem> handle(ResourceNotFoundException e) {
//        ErrorItem error = new ErrorItem();
//        error.setMessage(e.getMessage());
//
//        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
//    }
}
