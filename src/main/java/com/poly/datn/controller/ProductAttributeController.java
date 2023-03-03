package com.poly.datn.controller;


import com.poly.datn.dto.request.ProductAttributeRequest;
import com.poly.datn.service.ProductAttributeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.poly.datn.controller.router.Router.ADMIN_API.BASE;
import static com.poly.datn.controller.router.Router.ADMIN_API.PRODUCT_ATTRIBUTE;
@RestController
@RequestMapping(BASE+PRODUCT_ATTRIBUTE)
@RequiredArgsConstructor
@CrossOrigin(origins = {"http://localhost:3000"})
public class ProductAttributeController {

    private final ProductAttributeService productAttributeService;

    @GetMapping()
    public ResponseEntity<?> findAll(){
        return ResponseEntity.ok(productAttributeService.findAll());
    }
    @GetMapping("/{productId}")
    public ResponseEntity<?> findByProductId(@PathVariable("productId") Integer productId){
        return ResponseEntity.ok(productAttributeService.findByProductId(productId));
    }

    @PostMapping()
    public ResponseEntity<?> createProductAttribute(@RequestBody List<ProductAttributeRequest> productAttributeRequestList){
        return ResponseEntity.ok(productAttributeService.create(productAttributeRequestList));
    }

    @PutMapping()
    public ResponseEntity<?> updateProductAttribute(@RequestBody List<ProductAttributeRequest> productAttributeRequestList){
        return ResponseEntity.ok(productAttributeService.create(productAttributeRequestList));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProductAttribute(@PathVariable("id") Integer id){
        productAttributeService.delete(id);
        return ResponseEntity.ok().build();
    }

}
