package com.poly.datn.repository;

import com.poly.datn.entity.Account;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AccountRepository extends JpaRepository<Account, Integer> {
    Account findByUsername(String userName);

    @Query(value = "Select * from account",nativeQuery = true)
    List<Account> findAllUserAndAuth(Pageable pageable);
}