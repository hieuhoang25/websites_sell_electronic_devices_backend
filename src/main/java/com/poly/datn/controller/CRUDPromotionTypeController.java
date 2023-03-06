package com.poly.datn.controller;

import com.poly.datn.controller.router.Router;
import com.poly.datn.dto.request.PromotionTypeRequest;
import com.poly.datn.service.CRUDPromotionTypeService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.poly.datn.controller.router.Router.ADMIN_API.BASE;
import static com.poly.datn.controller.router.Router.ADMIN_API.PROMOTION_TYPE;


@RestController
@RequiredArgsConstructor
@RequestMapping(BASE+ PROMOTION_TYPE)
@Tag(name = BASE+ PROMOTION_TYPE)
public class CRUDPromotionTypeController {
    private final CRUDPromotionTypeService promotionTypeService;

    @GetMapping
    public ResponseEntity<?> fetchAll(){
        return ResponseEntity.ok(promotionTypeService.findAll());
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody PromotionTypeRequest promotionTypeRequest){
        return ResponseEntity.ok(promotionTypeService.create(promotionTypeRequest));
    }

    @PutMapping
    public ResponseEntity<?> update(@RequestBody PromotionTypeRequest promotionTypeRequest){
        return ResponseEntity.ok(promotionTypeService.update(promotionTypeRequest));
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> fetchOne(@PathVariable("id") Integer id){
        return ResponseEntity.ok(promotionTypeService.findById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Integer id){
        promotionTypeService.delete(id);
        return ResponseEntity.ok().build();
    }

}
