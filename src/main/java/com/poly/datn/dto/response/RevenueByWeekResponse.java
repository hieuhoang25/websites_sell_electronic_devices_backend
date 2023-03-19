package com.poly.datn.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.Map;

@Data
public class RevenueByWeekResponse {
    @JsonProperty("last_month")
    private Map<String, Object> lastMonth;

    @JsonProperty("this_month")
    private Map<String, Object> thisMonth;
}
