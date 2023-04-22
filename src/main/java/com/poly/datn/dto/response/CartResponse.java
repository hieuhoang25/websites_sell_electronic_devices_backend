package com.poly.datn.dto.response;

import java.io.Serializable;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import static java.util.Comparator.*;

@Getter
@Setter
@Slf4j
// @RequiredArgsConstructor
@NoArgsConstructor
@Builder(setterPrefix = "with")
@AllArgsConstructor
public class CartResponse implements Serializable {

    private Integer id;

    // private UserResponse user;

    private Instant create_date;

    private Double price_sum;

    private List<CartDetailResponse> cartDetails;

    public static CartResponseBuilder getAnnonCartResponseBuilder(Integer cartId) {
        List<CartDetailResponse> detailsList = new ArrayList<>();
        return new CartResponseBuilder().withId(cartId).withCartDetails(detailsList).withCreate_date(Instant.now())
                .withPrice_sum(0.0);
    }

    public CartResponse sortCartDetailsByCreateDateDesc() {
        try {
            cartDetails.sort(comparing(CartDetailResponse::getCreate_date, nullsLast((reverseOrder()))).thenComparing(CartDetailResponse::getId));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return this;
    }

    public CartResponse calPriceSum() {

        if(this.cartDetails == null || this.cartDetails.isEmpty()){
            this.price_sum = 0.0;
        } 
       else {
        Double sum = cartDetails.stream().mapToDouble((c) -> c.getPrice_detail() * c.getQuantity()).sum(); 
        this.price_sum = sum;
       }
       
        return this;
    }

}
