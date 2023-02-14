package com.poly.datn.controller;

import com.poly.datn.common.MessageResponse;
import com.poly.datn.common.ResponseBody;
import com.poly.datn.common.SearchCriteria;
import com.poly.datn.common.SearchResult;
import com.poly.datn.dto.response.ProductResponse;
import com.poly.datn.dto.response.UserFindAllResponse;
import com.poly.datn.entity.Product;
import com.poly.datn.service.UserFindAll;
import com.poly.datn.common.mapper.ModelConverter;
import com.poly.datn.controller.router.Router;

import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import  com.poly.datn.controller.router.Router.USER_API.*;

import static com.poly.datn.controller.router.Router.USER_API.FILTER;
import static com.poly.datn.controller.router.Router.USER_API.PRODUCT;

@RequiredArgsConstructor
@RestController
@RequestMapping(Router.USER_API.BASE)
public class UserFindAllController {
    private final UserFindAll userFindAll;
    private final ModelConverter modelConverter;
    @GetMapping()
    public List<UserFindAllResponse> userFindAll() {
        return modelConverter.mapAllByIterator(userFindAll.findAll(), UserFindAllResponse.class);
    }

    @GetMapping(Router.USER_API.FIND_BY)
    public ResponseEntity<UserFindAllResponse> findAllByID() {
        return null;
    }




    @GetMapping(PRODUCT+FILTER)
    public ResponseBody<SearchResult<ProductResponse>> findProductsByMultiFiels(@RequestBody  List<SearchCriteria> criteria, @RequestParam("size")  Integer size, @RequestParam("page") Integer page){
        Pageable pageable = PageRequest.of(page, size);
        return new ResponseBody<SearchResult<ProductResponse>>(1,MessageResponse.MESSAGE_SUCCESS, userFindAll.findProductsByMultiFiels(criteria, pageable));
    }
}
