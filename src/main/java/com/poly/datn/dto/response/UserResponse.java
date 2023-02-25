package com.poly.datn.dto.response;

import com.poly.datn.entity.*;
import com.poly.datn.repository.WishlistRepository;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.Instant;
import java.util.LinkedHashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserResponse implements Serializable {
    private Integer id;
    private String email;
    private String fullName;
    private String phone;
    private Instant createDate;
    private Instant updateDate;
    private Set<RatingResponse> ratings ;
    private Set<AccountResponse> accounts ;
    private Set<AddressResponse> addresses ;
//    private Set<PromotionUser> promotionUsers ;
//    private Set<Cart> carts ;
    //    private Set<WishlistRepository> wishlists ;
}
