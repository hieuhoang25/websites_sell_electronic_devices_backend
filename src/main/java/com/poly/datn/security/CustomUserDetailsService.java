package com.poly.datn.security;


import com.poly.datn.entity.Account;
import com.poly.datn.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by rajeevkumarsingh on 02/08/17.
 */

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
    private final AccountRepository accountRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Account account = accountRepository.findByUsername(username);
        if (account == null)
            throw new UsernameNotFoundException("Not found user");
        // Tạo UserDetails từ Account
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        account.getAuthorities().forEach(role -> authorities.add(new SimpleGrantedAuthority(role.getRole().getRoleName())));
        return UserPrincipal.create(account,authorities);
    }
}