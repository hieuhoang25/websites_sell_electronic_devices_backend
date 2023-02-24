package com.poly.datn.controller;

import com.poly.datn.dto.request.ProductRequest;
import com.poly.datn.entity.Product;
import com.poly.datn.service.CURDProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;

import static com.poly.datn.controller.router.Router.ADMIN_API.BASE;
import static com.poly.datn.controller.router.Router.ADMIN_API.PRODUCT;
@RestController
@RequestMapping(BASE+ PRODUCT)
@RequiredArgsConstructor
public class CURDProductController {

    private final CURDProductService curdProductService;

    @GetMapping()
    public ResponseEntity<?> fetchData() {
        return ResponseEntity.ok(curdProductService.findAll());
    }

    @PostMapping()
    public ResponseEntity<?> createProduct(@RequestBody ProductRequest productRequest){
        productRequest.setIs_delete(false);
        return ResponseEntity.ok(curdProductService.create(productRequest));
    }

    @PutMapping()
    public ResponseEntity<?> updateProduct(@RequestBody ProductRequest productRequest){
        return ResponseEntity.ok(curdProductService.update(productRequest));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable("id") Integer id){
        curdProductService.delete(id);
        return ResponseEntity.ok().build();
    }

}
