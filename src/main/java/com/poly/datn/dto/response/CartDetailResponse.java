package com.poly.datn.dto.response;

import java.io.Serializable;
import java.time.Instant;
import java.util.concurrent.ThreadLocalRandom;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.firebase.database.annotations.NotNull;
import com.poly.datn.dto.request.CartDetailRequest;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(setterPrefix = "with")
public class CartDetailResponse implements Serializable {

    @NotNull
    private Integer id;

    private ProductVariantResponse productVariant;

    private Double price_detail;

    private Double discount_amount;

    @NotNull
    private Integer quantity;

    @JsonIgnore
    private Instant create_date;

    public static CartDetailResponse getDeletedDetailResponse() {
        CartDetailResponse rp = new CartDetailResponse();
        rp.setId(-1);
        return rp;
    }

    public static CartDetailResponseBuilder getPlainCartDetailResponeBuilder(Integer id) {
        CartDetailResponseBuilder builder = new CartDetailResponseBuilder();
        builder.withId(id);
        return builder;
    }

    public static CartDetailResponseBuilder withCartPrice_Detail(CartDetailResponseBuilder builder) {
        Integer quantity = (builder.quantity == null) || (builder.quantity == 0)? 1 : builder.quantity;
        Double price_detail = builder.productVariant.getPrice() * quantity;
        return builder.withPrice_detail(price_detail);
    }
    public static CartDetailResponseBuilder withVariantDiscount_amount(CartDetailResponseBuilder builder) {
        ProductVariantResponse productVariant =  builder.productVariant;
        if (productVariant == null) {
            return builder.withDiscount_amount(0.0);
        }
        try {
            Double price = productVariant.getPrice();
            PromotionProductResponse promotionProduct = productVariant.getProduct_promotion();

            if (promotionProduct == null || (promotionProduct.getActivate() == null || !promotionProduct.getActivate()))
            return builder.withDiscount_amount(0.0);
            Boolean hasExpireDate =  promotionProduct.getExpiration_date() != null;  
            Instant today = Instant.now();
            if (hasExpireDate && today.isAfter(promotionProduct.getExpiration_date())) {
                return builder.withDiscount_amount(0.0);
            } 
            if (promotionProduct.getIs_percent()) {
                Integer per = promotionProduct.getDiscount_amount().intValue();
                return builder.withDiscount_amount(price * (per * 0.01));

            } else {
                return builder.withDiscount_amount(promotionProduct.getDiscount_amount());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;

    }

    public CartDetailRequest getRequestFromGuestCartDetail( Integer cartId) {

        Integer id = this.getId();
        Integer qty = this.getQuantity();
        Integer variantId = this.getProductVariant().getId();
        
        if(variantId == null) return null;

        if(id == null) {
            id = ThreadLocalRandom.current().nextInt(1, (Integer.MAX_VALUE - 1));
        }else if(qty == null || qty == 0) qty = 1;
        return new CartDetailRequest(id, cartId, qty,variantId);
    }
}
