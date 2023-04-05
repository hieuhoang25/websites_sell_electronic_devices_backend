package com.poly.datn.dto.response;

import java.io.Serializable;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import static java.util.Comparator.*;

@Getter
@Setter
// @RequiredArgsConstructor
@NoArgsConstructor
@Builder(setterPrefix = "with")
@AllArgsConstructor
public class CartResponse implements Serializable {

    private Integer id;

    // private UserResponse user;

    private Instant create_date;

    private Float price_sum;

    private List<CartDetailResponse> cartDetails;

    public static CartResponseBuilder getAnnonCartResponseBuilder(Integer cartId) {
        List<CartDetailResponse> detailsList = new ArrayList<>();
        return new CartResponseBuilder().withId(cartId).withCartDetails(detailsList).withCreate_date(Instant.now())
                .withPrice_sum(0.0f);
    }

    public CartResponse sortCartDetailsByCreateDateDesc() {
        try {
            cartDetails.sort(comparing(CartDetailResponse::getCreate_date, nullsLast((reverseOrder()))));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return this;
    }

}
