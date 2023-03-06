package com.poly.datn.controller;

import com.poly.datn.controller.router.Router;
import com.poly.datn.dto.response.OrderManagerResponse;
import com.poly.datn.service.OrderFindByStatusService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import java.util.List;

import static com.poly.datn.controller.router.Router.ADMIN_API.ORDER;

@RequiredArgsConstructor
@RestController
@RequestMapping(Router.ADMIN_API.BASE + ORDER)
@Validated
@Tag(name = Router.ADMIN_API.BASE + ORDER)
public class OrderFindByStatusController {
    private final OrderFindByStatusService service;

    @GetMapping("/{idStatus}")
    public ResponseEntity<List<OrderManagerResponse>> getOrder(
            @PathVariable @Valid @NotNull(message = "Không để trống tình trạng đơn hàng") Integer idStatus) {
        List<OrderManagerResponse> orderManagerResponse = service.getOrder(idStatus);
        if (orderManagerResponse.isEmpty())
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(orderManagerResponse);
    }
}
