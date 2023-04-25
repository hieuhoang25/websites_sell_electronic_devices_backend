package com.poly.datn.controller;

import com.poly.datn.controller.router.Router;
import com.poly.datn.service.OrderService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping(Router.ADMIN_API.BASE + Router.ADMIN_API.ORDER)
@Tag(name = Router.ADMIN_API.BASE + Router.ADMIN_API.ORDER)
public class OrderController {
    private OrderService orderService;

    @GetMapping()
    public ResponseEntity<?> fetchAll(@RequestParam("status") List<String> status){
        return ResponseEntity.ok(orderService.findAll(status));
    }

    @GetMapping("/count-status")
    public ResponseEntity<?> countOrders(){
        return ResponseEntity.ok(orderService.countOrdersByStatus());
    }
}
