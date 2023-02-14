package com.poly.datn.repository;

import com.poly.datn.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
    @Override
    @Query("select o from Category o where o.parent is null")
    List<Category> findAll();
}