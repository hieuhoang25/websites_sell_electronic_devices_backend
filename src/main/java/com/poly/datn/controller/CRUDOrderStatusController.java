package com.poly.datn.controller;

import com.poly.datn.controller.router.Router;
import com.poly.datn.dto.request.OrderStatusCreateRequest;
import com.poly.datn.dto.request.OrderStatusUpdateRequest;
import com.poly.datn.dto.response.OrderStatusResponse;
import com.poly.datn.service.CRUDOrderStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.List;

import static com.poly.datn.controller.router.Router.ADMIN_API.ORDER_STATUS;

@RequiredArgsConstructor
@RequestMapping(Router.ADMIN_API.BASE)
@RestController
@Validated
public class CRUDOrderStatusController {
    private final CRUDOrderStatus crudOrderStatus;

    @GetMapping(ORDER_STATUS)
    public ResponseEntity<List<OrderStatusResponse>> getOrderStatus() {
        return ResponseEntity.ok(crudOrderStatus.getOrderStatus());
    }

    @PostMapping(ORDER_STATUS)
    public ResponseEntity<OrderStatusResponse> createOrderStatus(@RequestBody @Valid OrderStatusCreateRequest request) {
        return ResponseEntity.ok(crudOrderStatus.create(request));
    }

    @PutMapping(ORDER_STATUS)
    public ResponseEntity<OrderStatusResponse> updateOrderStatus(@RequestBody @Valid OrderStatusUpdateRequest request) {
        return ResponseEntity.ok(crudOrderStatus.update(request));
    }


    @DeleteMapping(ORDER_STATUS)
    public ResponseEntity<?> deleteOrderStatus(@PathVariable @Valid @Min(1) @NotNull Integer id) {
        crudOrderStatus.delete(id);
        return ResponseEntity.noContent().build();
    }
}
