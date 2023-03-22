package com.poly.datn.dto.request;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class TokenRefreshRequest {
  
    private String refresh_token;
  
}
