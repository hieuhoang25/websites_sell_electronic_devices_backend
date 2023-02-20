package com.poly.datn.dto.response;

import com.poly.datn.entity.Category;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.Instant;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CategoryResponse implements Serializable {

    private Integer id;

    private String category_name;

    private Integer parent_id;

    private Instant create_date;

    private Instant update_date;

    private Set<CategoryResponse> categories;
}
