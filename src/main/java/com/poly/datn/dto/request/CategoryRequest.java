package com.poly.datn.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CategoryRequest implements Serializable {

    private Integer id;

    @NotNull
    @NotBlank(message = "{NotBlank.categoryName}")
    private String category_name;

    private Integer parent_id;

}
