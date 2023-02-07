package com.poly.datn.service.serviceImpl;

import com.poly.datn.entity.User;
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
    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }
}
