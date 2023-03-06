package com.poly.datn.service.serviceImpl;

import com.poly.datn.common.mapper.ModelConverter;
import com.poly.datn.dto.response.UserResponse;
import com.poly.datn.entity.Account;
import com.poly.datn.repository.AccountRepository;
import com.poly.datn.repository.UserRepository;
import com.poly.datn.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
@Service
@RequiredArgsConstructor
@Transactional
public class UserServiceImpl implements UserService , UserDetailsService {
    private final ModelConverter modelConverter;
    private  final UserRepository userRepository;
    private final AccountRepository accountRepository;
    @Override
    public List<UserResponse> findAll() {
        return modelConverter.mapAllByIterator(userRepository.findAll(),UserResponse.class);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Account account = accountRepository.findByUsername(username);
            if(account == null)
                throw new UsernameNotFoundException("Not found user");
        // Tạo UserDetails từ Account
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        account.getAuthorities().forEach(role -> authorities.add(new SimpleGrantedAuthority(role.getRole().getRoleName())));
        return new User(account.getUsername(),account.getPassword(), authorities);
    }
}
