package com.poly.datn.exception.cart;


/* 
 * Exception when variant in cart has status = false
*/
public class VariantUnavailable extends CartException{

    public VariantUnavailable(String message) {
        super(message);
    }
    
}
