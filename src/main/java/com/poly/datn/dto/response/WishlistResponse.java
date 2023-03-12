package com.poly.datn.dto.response;

import java.io.Serializable;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class WishlistResponse implements Serializable {

    public Integer id;

    public Integer product_id;
}
