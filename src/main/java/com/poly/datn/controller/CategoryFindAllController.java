package com.poly.datn.controller;

import com.poly.datn.common.MessageResponse;
import com.poly.datn.common.ResponseBody;
import com.poly.datn.dto.response.CategoryFindAllResponse;
import com.poly.datn.service.CategoryFindAll;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.poly.datn.controller.router.Router.USER_API.BASE;
import static com.poly.datn.controller.router.Router.USER_API.CATEGORY;

@RequiredArgsConstructor
@RequestMapping(BASE)
@RestController
public class CategoryFindAllController {
    private final CategoryFindAll categoryFindAll;

    @GetMapping(CATEGORY)
    public ResponseEntity<List<CategoryFindAllResponse>> categoryFindAllResponse() {
        return ResponseEntity.ok(categoryFindAll.getCategory());
    }
}
