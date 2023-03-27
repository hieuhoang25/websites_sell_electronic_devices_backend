package com.poly.datn.dto.request;


import com.poly.datn.dto.response.OrderStatusResponse;
import com.poly.datn.dto.response.OrdersUserResponse;
import lombok.Data;

@Data
public class OderStatusChangeRequest {
     private OrdersUserResponse order;

}
