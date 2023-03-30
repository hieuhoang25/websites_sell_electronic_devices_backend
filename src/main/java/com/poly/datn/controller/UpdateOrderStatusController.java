package com.poly.datn.controller;

import com.poly.datn.controller.router.Router.ADMIN_API;
import com.poly.datn.controller.router.Router.USER_API;
import com.poly.datn.service.UpdateOrderStatusService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import static com.poly.datn.controller.router.Router.ADMIN_API.ORDER;

@RestController
@RequiredArgsConstructor
public class UpdateOrderStatusController {
    private final UpdateOrderStatusService service;

    @PutMapping(ADMIN_API.BASE + ORDER + "/{idOrder}")
    @Tag(name = ADMIN_API.BASE + ORDER)
    public ResponseEntity setOrderStatusToReceive(
            @PathVariable @Valid @NotNull(message = "idOrder can not be null") Integer idOrder) {
        service.updateStatusToReceive(idOrder);
        return ResponseEntity.noContent().build();
    }

    @PutMapping(USER_API.BASE + ORDER + "/{idOrder}")
    @Tag(name = USER_API.BASE + ORDER)
    public ResponseEntity setOrderStatusToCompleted(
            @PathVariable @Valid @NotNull(message = "idOrder can not be null") Integer idOrder) {
        service.updateStatusToCompleted(idOrder);
        return ResponseEntity.noContent().build();
    }
}
