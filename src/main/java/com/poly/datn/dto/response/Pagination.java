package com.poly.datn.dto.response;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Pagination<T> {
    private int size;
    private int page;
    private int totalPage;
    private List<T> data;
}
