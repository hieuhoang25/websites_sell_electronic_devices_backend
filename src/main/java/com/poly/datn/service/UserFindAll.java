package com.poly.datn.service;


import com.poly.datn.dto.response.UserFindAllResponse;

import java.util.List;

public interface UserFindAll {
    List<UserFindAllResponse> getUser();
}
