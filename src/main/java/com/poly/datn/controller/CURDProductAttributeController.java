package com.poly.datn.controller;


import com.poly.datn.dto.request.ProductAttributeRequest;
import com.poly.datn.service.ProductAttributeService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.poly.datn.controller.router.Router.ADMIN_API.BASE;
import static com.poly.datn.controller.router.Router.ADMIN_API.PRODUCT_ATTRIBUTE;
@RestController
@RequestMapping(BASE+PRODUCT_ATTRIBUTE)
@RequiredArgsConstructor
@Tag(name = BASE+PRODUCT_ATTRIBUTE)
public class CURDProductAttributeController {

    private final ProductAttributeService productAttributeService;

//    @GetMapping()
//    public ResponseEntity<?> findAll(){
//        return ResponseEntity.ok(productAttributeService.findAll());
//    }
    @GetMapping("/{productId}")
    public ResponseEntity<?> findByProductId(@PathVariable("productId") Integer productId){

        return ResponseEntity.ok(productAttributeService.findByProductId(productId));
    }
//    produces = {"application/json"}
    @PostMapping()
    public ResponseEntity<?> createProductAttribute(@RequestBody ProductAttributeRequest productAttributeRequestList){

        return ResponseEntity.ok(productAttributeService.create(productAttributeRequestList));
    }


    @PutMapping()
    public ResponseEntity<?> updateProductAttribute(@RequestBody List<ProductAttributeRequest> productAttributeRequestList){
        return ResponseEntity.ok(productAttributeService.update(productAttributeRequestList));
    }
    @DeleteMapping()
    public ResponseEntity<?> deleteProductAttribute(@RequestBody List<Integer> listId){
        productAttributeService.delete(listId);
        return ResponseEntity.ok().build();
    }

}
