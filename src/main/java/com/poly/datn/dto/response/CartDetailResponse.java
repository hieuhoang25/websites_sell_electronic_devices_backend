package com.poly.datn.dto.response;

import java.io.Serializable;
import java.util.List;

import org.modelmapper.Converters.Converter;

import com.google.firebase.database.annotations.NotNull;
import com.poly.datn.entity.CartDetail;
import com.poly.datn.entity.ProductVariant;

import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
public class CartDetailResponse implements Serializable{
    

    @NotNull
    private Integer id;

    
    private ProductVariantResponse productVariant;


    private Double price_detail;



    @NotNull
    private Integer quantity;

    public static CartDetailResponse getDeletedDetailResponse() {
        CartDetailResponse rp = new CartDetailResponse();
        rp.setId(-1);
        return rp;
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
