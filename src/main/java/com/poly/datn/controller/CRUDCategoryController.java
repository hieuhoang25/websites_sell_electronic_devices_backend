package com.poly.datn.controller;

import com.poly.datn.dto.request.CategoryRequest;
import com.poly.datn.dto.response.ErrorResponse;
import com.poly.datn.repository.CategoryRepository;
import com.poly.datn.service.CRUDCategoryService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.poly.datn.controller.router.Router.ADMIN_API.BASE;
import static com.poly.datn.controller.router.Router.ADMIN_API.CATEGORY;

@RestController
@RequestMapping(BASE+CATEGORY)
@RequiredArgsConstructor
@Tag(name = BASE+CATEGORY)
public class CRUDCategoryController {

    private final CRUDCategoryService cudCategoryService;
    private final CategoryRepository categoryRepository;


    @GetMapping()
    public ResponseEntity<?> fetchData(){
        return ResponseEntity.ok(cudCategoryService.findAllParentAndChild());
//        return ResponseEntity.ok(cudCategoryService.findAll());
    }

    @GetMapping ("/{id}")
    public ResponseEntity<?> fetchOne(@PathVariable("id") Integer id){
        return ResponseEntity.ok(cudCategoryService.findById(id));
    }
    @PostMapping()
    public ResponseEntity<?> createCategory(@RequestBody CategoryRequest categoryRequest){
        return ResponseEntity.ok(cudCategoryService.create(categoryRequest));
    }
    @PutMapping ()
    public ResponseEntity<?> updateCategory(@RequestBody CategoryRequest categoryRequest){
        return ResponseEntity.ok(cudCategoryService.update(categoryRequest));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?>deleteCategory(@PathVariable("id") Integer id){
        if(categoryRepository.checkCanDeleteChildCategory(id) > 0){
            return ResponseEntity.badRequest().body(new ErrorResponse("Bạn không thể xoá danh mục này"));
        }else if(categoryRepository.checkCanDeleteParentCategory(id) > 0){
            return ResponseEntity.badRequest().body(new ErrorResponse("Danh mục cha phải không tồn tại 1 danh mục con nào"));
        }else{
            cudCategoryService.delete(id);
            return ResponseEntity.ok("deleted success");
        }
    }
}
