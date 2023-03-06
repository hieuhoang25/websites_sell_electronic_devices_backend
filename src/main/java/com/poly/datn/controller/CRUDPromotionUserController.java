package com.poly.datn.controller;

import com.poly.datn.controller.router.Router;
import com.poly.datn.dto.request.PromotionUserRequest;
import com.poly.datn.service.CRUDPromotionUserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(Router.ADMIN_API.BASE+ Router.ADMIN_API.PROMOTION_USER)
@Tag(name = Router.ADMIN_API.BASE+ Router.ADMIN_API.PROMOTION_USER)
public class CRUDPromotionUserController {
    private final CRUDPromotionUserService crudPromotionUserService;

    @GetMapping()
    public ResponseEntity<?> fetchAll(){
        return ResponseEntity.ok(crudPromotionUserService.findAll());
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> fetchOne(@PathVariable("id") Integer id){
        return ResponseEntity.ok(crudPromotionUserService.findById(id));
    }
    @PostMapping()
    public ResponseEntity<?> create(@RequestBody PromotionUserRequest promotionUserRequest){
        return ResponseEntity.ok(crudPromotionUserService.create(promotionUserRequest));
    }
    @PutMapping()
    public ResponseEntity<?> update(@RequestBody PromotionUserRequest promotionUserRequest){
        return ResponseEntity.ok(crudPromotionUserService.update(promotionUserRequest));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable("id") Integer id){
        crudPromotionUserService.delete(id);
        return ResponseEntity.ok().build();
    }

}
