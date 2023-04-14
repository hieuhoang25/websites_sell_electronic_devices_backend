package com.poly.datn.controller;

import com.poly.datn.controller.router.Router;
import com.poly.datn.dto.request.OrderDetailRequest;
import com.poly.datn.service.CancelOrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

import static com.poly.datn.controller.router.Router.USER_API.CANCEL_ORDER;

@RestController
@RequiredArgsConstructor
@RequestMapping(Router.USER_API.BASE + CANCEL_ORDER)
@Validated
public class CancelOrderController {
    private final CancelOrderService service;
    @PutMapping
    public ResponseEntity cancelOrder(@Valid @NotNull Integer idOrder,
                                      @RequestBody @NotNull List< @Valid OrderDetailRequest> request){
        service.cancelOrder(idOrder,request);
        return ResponseEntity.noContent().build();
    }
}
