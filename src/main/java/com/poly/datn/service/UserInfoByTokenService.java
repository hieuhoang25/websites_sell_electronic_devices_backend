package com.poly.datn.service;

import com.poly.datn.dto.request.UserInfoRequest;
import com.poly.datn.dto.response.UserLoggedResponse;
import com.poly.datn.entity.User;

public interface UserInfoByTokenService {
    UserLoggedResponse getUserInfo();

    // get user entity of current logged-in user
    User getCurrentUser();

    UserLoggedResponse updateUserInformation(UserInfoRequest request);
}
