package com.poly.datn.dto.request;


import com.poly.datn.dto.response.OrderStatusResponse;
import com.poly.datn.dto.response.OrdersUserResponse;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class OderStatusChangeRequest {
     private OrdersUserResponse order;
}
