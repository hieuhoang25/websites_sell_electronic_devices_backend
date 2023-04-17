package com.poly.datn.dto.response;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.poly.datn.common.mapper.ModelConverter;
import com.poly.datn.entity.Product;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(setterPrefix = "with")
public class FlashDealResponse implements Serializable{

    private LocalDateTime current_time;
    private LocalDateTime start_time;

    private LocalDateTime expired_time;

    private boolean activate;
   
    private String name;
    private List<ProductFlashDealResponse> products;
    private Boolean start;
    public FlashDealResponse  setProductResponseList(Set<Product> listProducts) {
        ModelConverter converter = new ModelConverter();
      
        if(listProducts == null) {
            setProductsList(null); return this;
        }
        List<ProductFlashDealResponse> convertedList = converter.mapAllByIterator(listProducts, ProductFlashDealResponse.class);
        setProductsList(convertedList);
        return this;
    }

    // @Override
    public void setProductsList(List<ProductFlashDealResponse> products) {
        if(products != null) {
            this.products = new ArrayList<>(products);
        }else {
            this.products =  new ArrayList<>();
        }    
    }
    
}
