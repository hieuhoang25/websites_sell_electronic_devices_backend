package com.poly.datn.dto.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class UserFacebookReponse {
    @JsonProperty("email")
    public String email;

    @JsonProperty("name")
    public String fullname;
}