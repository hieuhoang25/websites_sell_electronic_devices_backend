package com.poly.datn.controller;

import com.poly.datn.common.MessageResponse;
import com.poly.datn.common.ResponseBody;
import com.poly.datn.dto.request.ProductRequest;
import com.poly.datn.service.CURDProductService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.Optional;

import static com.poly.datn.controller.router.Router.ADMIN_API.BASE;
import static com.poly.datn.controller.router.Router.ADMIN_API.PRODUCT;
@RestController
@RequestMapping(BASE+ PRODUCT)
@RequiredArgsConstructor
@Tag(name = BASE+ PRODUCT)
public class CURDProductController {

    private final CURDProductService curdProductService;

    @GetMapping()
    public ResponseEntity<?> fetchData(@RequestParam("size")Optional<Integer> size,
            @RequestParam("page") Optional<Integer> page) {
        Pageable pageable = PageRequest.of(page.orElse(0),size.orElse(10));
        return ResponseEntity.ok(
                new ResponseBody<>(
                        1,
                        MessageResponse.MESSAGE_SUCCESS,
                        curdProductService.findAll(pageable)
                )
        );
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
