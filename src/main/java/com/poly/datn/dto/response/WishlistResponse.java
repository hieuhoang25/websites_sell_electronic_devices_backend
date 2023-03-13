package com.poly.datn.dto.response;

import java.io.Serializable;
import java.time.Instant;

import com.poly.datn.entity.Product;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class WishlistResponse implements Serializable {

    public Integer id;
    
    // public Integer product_id;
    
    public Integer product_id;
    public String product_name;
    public String product_image;
    public String product_category_name;

    public Instant updateDate;
    public Instant createDate;

}
