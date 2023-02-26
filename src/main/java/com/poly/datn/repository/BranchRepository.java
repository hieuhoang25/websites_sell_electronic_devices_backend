package com.poly.datn.repository;

import com.poly.datn.entity.Brand;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BranchRepository extends JpaRepository<Brand, Integer> {
}
