package com.poly.datn.controller;

import com.poly.datn.common.mapper.ModelConverter;
import com.poly.datn.controller.router.Router;
import com.poly.datn.dto.response.UserFindAllResponse;
import com.poly.datn.service.UserFindAll;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping(Router.USER_API.BASE)
public class UserFindAllController {
    private final UserFindAll userFindAll;
    private final ModelConverter modelConverter;

    @GetMapping
    public List<UserFindAllResponse> userFindAll() {
        return modelConverter.mapAllByIterator(userFindAll.findAll(), UserFindAllResponse.class);
    }
}
