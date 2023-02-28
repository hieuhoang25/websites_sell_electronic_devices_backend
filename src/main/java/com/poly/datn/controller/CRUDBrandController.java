package com.poly.datn.controller;

import com.poly.datn.dto.request.BrandRequest;
import com.poly.datn.service.CRUDBrandService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import static com.poly.datn.controller.router.Router.BRANCH_API.BASE;

@RestController
@RequestMapping(BASE)
@RequiredArgsConstructor
public class CRUDBrandController {
    private final CRUDBrandService crudBrandService;

    @GetMapping()
    @Tag(name = "Brand CRUD")
    public ResponseEntity<?> listBrand(){
        return ResponseEntity.ok(crudBrandService.findAll());
    }

    @PostMapping
    @Tag(name = "Brand CRUD")
    public ResponseEntity<?> createBrand(@Valid  @RequestBody BrandRequest brandRequest){
        return ResponseEntity.ok(crudBrandService.createBranch(brandRequest));
    }

    @PutMapping()
    @Tag(name = "Brand CRUD")
    public ResponseEntity<?> updateBrand(@Valid @RequestBody BrandRequest brandRequest){
        return ResponseEntity.ok(crudBrandService.updateById(brandRequest));

    }

    @GetMapping("products-of-brand/{id}")
    @Tag(name = "Brand CRUD")
    public ResponseEntity<?> listProductOfBrand(@PathVariable("id") @NotNull Integer id){
        return ResponseEntity.ok(crudBrandService.listProductOfBrand(id));
    }

    @DeleteMapping("/{id}")
    @Tag(name = "Brand CRUD")
    public ResponseEntity<?> deleteBrand(@PathVariable("id") @NotNull Integer id){
        crudBrandService.delete(id);
        return ResponseEntity.ok().build();
    }

}
