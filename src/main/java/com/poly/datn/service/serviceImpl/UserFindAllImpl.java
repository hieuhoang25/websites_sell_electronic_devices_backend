package com.poly.datn.service.serviceImpl;

import com.poly.datn.common.mapper.ModelConverter;
import com.poly.datn.dto.response.UserFindAllResponse;
import com.poly.datn.repository.UserRepository;
import com.poly.datn.service.UserFindAll;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Transactional(rollbackFor = Exception.class)
@Service
public class UserFindAllImpl implements UserFindAll {
    private final UserRepository userRepository;
    private final ModelConverter modelConverter;

    @Override
    public List<UserFindAllResponse> getUser() {
        return modelConverter.mapAllByIterator(userRepository.findAll(), UserFindAllResponse.class);
    }
}
