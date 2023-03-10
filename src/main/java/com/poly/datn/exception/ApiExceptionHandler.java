package com.poly.datn.exception;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageConversionException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.fasterxml.jackson.databind.exc.UnrecognizedPropertyException;
import com.poly.datn.common.ErrorItem;
import com.poly.datn.common.ErrorResponse;
import com.poly.datn.exception.cart.CartException;
import com.poly.datn.exception.cart.VariantAlreadyInCartException;

@RestControllerAdvice
public class ApiExceptionHandler {

    @Autowired
    private MessageSource messageSource;

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ErrorResponse> handle(ConstraintViolationException e) {
        ErrorResponse errors = new ErrorResponse();
        for (ConstraintViolation violation : e.getConstraintViolations()) {
            ErrorItem error = new ErrorItem();
            error.setCode(violation.getMessageTemplate());
            error.setMessage(violation.getMessage());
            errors.addError(error);
        }
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class) // lỗi do mình custom
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            /*
             * * default
             * String message = error.getDefaultMessage();
             * errors.put(fieldName, message);
             */
            String messageLocale = messageSource.getMessage((FieldError) error, Locale.getDefault());
            errors.put(fieldName, messageLocale);
        });
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
    }

    @ExceptionHandler(RuntimeException.class) // lỗi do mình custom
    protected ResponseEntity<Object> handleExceptionRuntime(RuntimeException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }

    // Dùng để validate ResquestBody -> trường hợp các field dư không map với Object
    @ExceptionHandler(HttpMessageConversionException.class)
    protected ResponseEntity<Object> handleUnknowProperitesInRequestBody(HttpMessageConversionException ex) {
        Throwable cause = ex.getCause();
        String errorMessage = "Can't read JSON";
        if (cause instanceof UnrecognizedPropertyException) {
            UnrecognizedPropertyException ec = (UnrecognizedPropertyException) cause;
            errorMessage = String.format("Unknow property %s at %d\nAPI only accepted JSON Object with properties %s",
                    ec.getPropertyName(), ec.getLocation().getLineNr(),Arrays.toString(ec.getKnownPropertyIds().toArray()));
        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMessage);
    }

    @ExceptionHandler(VariantAlreadyInCartException.class)
    protected ResponseEntity<Object> handleVariantAlreadyInCartException(VariantAlreadyInCartException ex) {

        String message = "Product already in cart, try to update if need to modify cart's item";
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
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
