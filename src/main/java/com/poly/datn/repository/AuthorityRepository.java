package com.poly.datn.repository;

import com.poly.datn.entity.Authority;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;

public interface AuthorityRepository extends JpaRepository<Authority, Integer> {

    @Modifying
    @Query(value = "UPDATE authority SET role_id = ?1 where " +
            "account_id = ?2 LIMIT 1",nativeQuery = true)
    void setRole(Integer roleId
            ,Integer accountId);
}