package com.poly.datn.dto.response;

import com.poly.datn.entity.Category;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Set;

/**
 * A DTO for the {@link com.poly.datn.entity.Category} entity
 */
@Data
@NoArgsConstructor
public class CategoryFindAllResponse implements Serializable {
    private Integer id;
    @Size(max = 100)
    private String category_name;
    private Set<CategoryFindAllResponse> categories;
}