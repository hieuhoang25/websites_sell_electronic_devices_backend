package com.poly.datn.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SearchResult<T> {
    private int size;
    private int page;
    private int totalPage;
    private List<T> data;
}
