package com.poly.datn.controller;


import com.poly.datn.controller.router.Router;
import com.poly.datn.dto.request.OderStatusChangeRequest;
import com.poly.datn.service.OrderStatusChangeService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(Router.ADMIN_API.BASE + Router.ADMIN_API.ORDER)
@Tag(name = Router.ADMIN_API.BASE + Router.ADMIN_API.ORDER)
@AllArgsConstructor
public class OderStatusChangeController {
    private OrderStatusChangeService oderStatusChangeService;

    @PostMapping(Router.ADMIN_API.ORDER_STATUS_CHANGE)
    public ResponseEntity<?> change(@RequestBody  OderStatusChangeRequest request){
        oderStatusChangeService.changeOrderStatus(request);
        return ResponseEntity.ok("Cập nhật trạng thái thành công");
    }
}
