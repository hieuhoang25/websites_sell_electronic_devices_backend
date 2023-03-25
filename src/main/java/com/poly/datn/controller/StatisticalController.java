package com.poly.datn.controller;


import com.poly.datn.service.StatisticalService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.poly.datn.controller.router.Router.ADMIN_API.*;

@RestController
@RequestMapping(BASE+ STATISTIC)
@RequiredArgsConstructor
@Tag(name = BASE+ STATISTIC)
public class StatisticalController {

    private final StatisticalService statisticalService;

    @GetMapping("/revenuebyweek")
    public ResponseEntity<?> revenueByWeek(){
        return ResponseEntity.ok(statisticalService.statisticRevenueByWeek());
    }

    @GetMapping("/statistic-pecially")
    public ResponseEntity<?> statisticSpecially(){
        return ResponseEntity.ok(statisticalService.statisticSpecially());
    }
    @GetMapping("selling-top")
    public ResponseEntity<?> statisticProductSellingTop(){
        return ResponseEntity.ok(statisticalService.statisticProductSellingTop());
    }
}
