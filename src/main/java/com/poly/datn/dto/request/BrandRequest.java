package com.poly.datn.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BrandRequest implements Serializable {
    private Integer id;

    @Size(max = 255)
    @NotBlank(message = "Brand name is not blank")
    @NotNull(message = "Brand name is not null")
    
    private String brand_name;

    private String image;
}
