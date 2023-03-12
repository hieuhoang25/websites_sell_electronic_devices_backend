package com.poly.datn.dto.response;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;

@Data
@Entity
public class ProductSellingTop {
    @Id
    private Serializable id;

    private String name;

    private Double revenne;

    private Integer stock;
}
