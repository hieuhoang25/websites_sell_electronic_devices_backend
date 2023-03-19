package com.poly.datn.controller;

import com.poly.datn.common.MessageResponse;
import com.poly.datn.common.ResponseBody;
import com.poly.datn.dto.request.ProductRequest;
import com.poly.datn.service.CURDProductService;
import com.poly.datn.service.serviceImpl.FileServiceImpl;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.tags.Tag;



import java.util.Optional;

import static com.poly.datn.controller.router.Router.ADMIN_API.BASE;
import static com.poly.datn.controller.router.Router.ADMIN_API.PRODUCT;
@RestController
@RequestMapping(BASE+ PRODUCT)
@RequiredArgsConstructor
@Tag(name = BASE+ PRODUCT)
public class CURDProductController {

    private final CURDProductService curdProductService;

    private final FileServiceImpl fileService;

    @GetMapping()
    public ResponseEntity<?> fetchData(@RequestParam("size")Optional<Integer> size,
            @RequestParam("page") Optional<Integer> page) {
        Pageable pageable = PageRequest.of(page.orElse(0),size.orElse(10));
        return ResponseEntity.ok     (
                new ResponseBody<>(
                        1,
                        MessageResponse.MESSAGE_SUCCESS,
                        curdProductService.findAll(pageable)
                )
        );
    }

    @GetMapping("/search")
    public ResponseEntity<?> fetchDataFilter(@RequestParam("size")Optional<Integer> size,
            @RequestParam("page") Optional<Integer> page,
                                       @RequestParam("search") Optional<String> search,
                                       @RequestParam("isDelete") Optional<Integer> isDeleted) {

        Pageable pageable = PageRequest.of(page.orElse(0),size.orElse(10));
        if(isDeleted.get() != -1){

            return ResponseEntity.ok(
                    new ResponseBody<>(
                            1,
                            MessageResponse.MESSAGE_SUCCESS,
                            curdProductService.findAllByFilterWithDeleted(pageable,"%"+search.get()+"%",isDeleted.get())
                    )
            );
        }else {

            return ResponseEntity.ok(
                    new ResponseBody<>(
                            1,
                            MessageResponse.MESSAGE_SUCCESS,
                            curdProductService.findAllByFilter(pageable,"%"+search.get()+"%")
                    )
            );

        }

    }


    @GetMapping("/{id}")
    public ResponseEntity<?> findProductById(@PathVariable("id") Integer id){
        return ResponseEntity.ok(curdProductService.findById(id));
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

    @DeleteMapping("/{id}/{isDeleted}")
    public ResponseEntity<?> deleteProduct(@PathVariable("id") Integer id,@PathVariable("isDeleted") Integer isDeleted){
        curdProductService.delete(id,isDeleted);
        return ResponseEntity.ok().build();
    }

}
