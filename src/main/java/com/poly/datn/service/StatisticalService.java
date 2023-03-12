package com.poly.datn.service;

import com.poly.datn.dto.response.ProductSellingTop;
import com.poly.datn.dto.response.RevenueByWeekResponse;
import com.poly.datn.dto.response.SpeciallyStatisticalResponse;

import java.util.List;

public interface StatisticalService {

    RevenueByWeekResponse statisticRevenueByWeek();

    SpeciallyStatisticalResponse statisticSpecially();


    List<ProductSellingTop> statisticProductSellingTop();


}
