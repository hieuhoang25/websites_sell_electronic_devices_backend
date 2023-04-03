package com.poly.datn.controller;

import com.poly.datn.common.SearchCriteria;
import com.poly.datn.common.SearchResult;
import com.poly.datn.dto.response.ProductFilterResponse;
import com.poly.datn.service.ProductFindByMultiField;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.List;
import java.util.Optional;

import static com.poly.datn.controller.router.Router.API.*;

@RequiredArgsConstructor
@RestController
@RequestMapping(BASE + PRODUCT)
@Tag(name = BASE + PRODUCT)
@Validated
public class ProductFilterController {
    private final ProductFindByMultiField productFindByMultiField;

    @PostMapping( FILTER)
    public ResponseEntity<SearchResult<ProductFilterResponse>>
    productFindByMultiField(@RequestBody List<SearchCriteria> criteria,
                            @RequestParam("size") Optional<Integer> size,
                            @RequestParam("page") Optional<Integer> page,
                            @RequestParam(required = false) String sortField,
                            @RequestParam(required = false) String sortType) {
        Pageable pageable =
                PageRequest.of(page.orElse(0), size.orElse(9));
        return ResponseEntity.ok(productFindByMultiField.findByMultiField(criteria, pageable,sortField,sortType));
    }
}
