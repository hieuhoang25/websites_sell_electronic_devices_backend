package com.poly.datn.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.Map;

@Data
public class RevenueByWeekResponse {
    @JsonProperty("last_week")
    private Map<String, Object> lastWeek;

    @JsonProperty("this_week")
    private Map<String, Object> thisWeek;
}
