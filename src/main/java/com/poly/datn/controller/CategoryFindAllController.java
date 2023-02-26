package com.poly.datn.controller;

import com.poly.datn.dto.response.CategoryFindAllResponse;
import com.poly.datn.service.CategoryFindAll;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.poly.datn.controller.router.Router.API.BASE;
import static com.poly.datn.controller.router.Router.API.CATEGORY;

@RequiredArgsConstructor
@RequestMapping(BASE)
@RestController
public class CategoryFindAllController {
    private final CategoryFindAll categoryFindAll;

    @GetMapping(CATEGORY)
    @Tag(name = "category")
    public ResponseEntity<List<CategoryFindAllResponse>> categoryFindAllResponse() {
        return ResponseEntity.ok(categoryFindAll.getCategory());
    }
}
