package com.poly.datn.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.poly.datn.entity.PromotionUser;

public interface PromotionUserRepository extends JpaRepository<PromotionUser, Integer> , JpaSpecificationExecutor {
    // List<PromotionUser> findAllByUserId(Integer userId);
}