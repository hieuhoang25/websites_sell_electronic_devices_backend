package com.poly.datn.dto.response;

import com.poly.datn.entity.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.Instant;
import java.util.LinkedHashSet;
import java.util.Set;

@Data
@NoArgsConstructor
public class ProductListResponse implements Serializable {
    private String productName;

    private String description;

    private Instant createDate;

    private Instant updateDate;

    private Category category;

    private Brand brand;

    private ProductType type;

    private String image;

    private Set<Wishlist> wishlists = new LinkedHashSet<>();

    private Set<Rating> ratings = new LinkedHashSet<>();

    private Set<ProductVariant> productVariants = new LinkedHashSet<>();

}
