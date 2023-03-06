package com.poly.datn.service.serviceImpl;

import com.poly.datn.common.mapper.ModelConverter;
import com.poly.datn.dto.response.UserResponse;
import com.poly.datn.repository.AccountRepository;
import com.poly.datn.repository.UserRepository;
import com.poly.datn.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
@Service
@RequiredArgsConstructor
@Transactional
public class UserServiceImpl implements UserService{
    private final ModelConverter modelConverter;
    private  final UserRepository userRepository;
    @Override
    public List<UserResponse> findAll() {
        return modelConverter.mapAllByIterator(userRepository.findAll(),UserResponse.class);
    }
}
