package com.poly.datn.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PaginationOrderTracking<T> implements Serializable {
    private int size;
    private int page;
    private int totalPage;
    private Long totalElement;
    private List<T> data;
}
