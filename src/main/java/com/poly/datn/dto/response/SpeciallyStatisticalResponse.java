package com.poly.datn.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class SpeciallyStatisticalResponse {
    @JsonProperty("number_of_user")
    private Integer numberOfUser;

    @JsonProperty("week_envenue")
    private Double weekEnvenue;

    @JsonProperty("product_selled")
    private Integer productSelled;

    @JsonProperty("order_yet_approved")
    private Integer orderYetApproved;


}
