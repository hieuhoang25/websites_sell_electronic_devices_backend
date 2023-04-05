package com.poly.datn.dto.response;

import java.io.Serializable;
import java.time.Instant;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.firebase.database.annotations.NotNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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
            if (promotionProduct == null) {

                return builder.withDiscount_amount(0.0);
            }

            if (promotionProduct.getIs_percent()) {
                Integer per = promotionProduct.getDiscount_amount().intValue();
                return builder.withDiscount_amount(price * (per * 0.01));

            } else {
                return builder.withDiscount_amount(price - promotionProduct.getDiscount_amount());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;

    }

    // private Integer total;

    // private PromotionProductResponse promotion;

    // private String category_name;

    // private String product_name;

    // private String sku_name;

    // @NotNull
    // private Integer productVariant_id;

    // private String productVairant_name;

    // private String color_name;

    // private String storage_name;

    // private String display_name;

}
