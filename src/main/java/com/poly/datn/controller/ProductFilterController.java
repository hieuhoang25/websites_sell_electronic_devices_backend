package com.poly.datn.controller;

import com.poly.datn.common.MessageResponse;
import com.poly.datn.common.ResponseBody;
import com.poly.datn.common.SearchCriteria;
import com.poly.datn.common.SearchResult;
import com.poly.datn.controller.router.Router;
import com.poly.datn.dto.response.ProductFilterResponse;
import com.poly.datn.service.ProductFindByMultiField;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import java.util.List;
import java.util.Optional;

import static com.poly.datn.controller.router.Router.USER_API.FILTER;
import static com.poly.datn.controller.router.Router.USER_API.PRODUCT;

@RequiredArgsConstructor
@RestController
@RequestMapping(Router.USER_API.BASE)
@Validated
public class ProductFilterController {
    private final ProductFindByMultiField productFindByMultiField;

    @GetMapping(PRODUCT + FILTER)
    public ResponseBody<SearchResult<ProductFilterResponse>>
    productFindByMultiField(@RequestBody @NotEmpty(message = "Khong de criteria rong") List<@Valid SearchCriteria> criteria,
                            @RequestParam("size") Optional<Integer> size,
                            @RequestParam("page") Optional<Integer> page) {
        Pageable pageable = PageRequest.of(page.orElse(0), size.orElse(1));
        return new ResponseBody<>
                (1, MessageResponse.MESSAGE_SUCCESS, productFindByMultiField.findByMultiField(criteria, pageable));
    }
}
