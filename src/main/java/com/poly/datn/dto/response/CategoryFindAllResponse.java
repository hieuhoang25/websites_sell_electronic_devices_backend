package com.poly.datn.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Set;

/**
 * A DTO for the {@link com.poly.datn.entity.Category} entity
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CategoryFindAllResponse implements Serializable {
    private Integer key;
    private String title;
    private Set<CategoryFindAllResponse> children;
}