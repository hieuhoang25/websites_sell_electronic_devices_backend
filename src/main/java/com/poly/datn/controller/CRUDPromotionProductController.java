package com.poly.datn.controller;

import com.poly.datn.controller.router.Router;
import com.poly.datn.dto.request.PromotionProductRequest;
import com.poly.datn.service.CRUDPromotionProductService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(Router.ADMIN_API.BASE+ Router.ADMIN_API.PROMOTION_PRODUCT)
@Tag(name = Router.ADMIN_API.BASE+ Router.ADMIN_API.PROMOTION_PRODUCT)
public class CRUDPromotionProductController {

    private final CRUDPromotionProductService promotionProductService;

    @GetMapping
    public ResponseEntity<?> fetchAll(){
        return ResponseEntity.ok(promotionProductService.findAll());
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> fetchOne(@PathVariable("id") Integer id){
        return ResponseEntity.ok(promotionProductService.findById(id));
    }
    @PostMapping
    public ResponseEntity<?> create(@RequestBody PromotionProductRequest  promotionProductRequest){
        return ResponseEntity.ok(promotionProductService.create(promotionProductRequest));

    }
    @PutMapping
    public ResponseEntity<?> modify(@RequestBody PromotionProductRequest  promotionProductRequest){
        return ResponseEntity.ok(promotionProductService.update(promotionProductRequest));

    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Integer id){
        promotionProductService.delete(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

}
