package com.poly.datn.repository;

import com.poly.datn.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
    @Query("select o from Category o where o.parent is null")
    List<Category> findAll();

    @Query(value = "select * from category",nativeQuery = true)
    List<Category> findAllParentAndChild();


    @Query(value = "select count(*) from category c join product p on c.id = p.category_id where c.id = :id",nativeQuery = true)
    Integer checkCanDeleteChildCategory(@Param("id") Integer id );

    @Query(value = "select count(*)from category a join category b on a.id = b.parent_id where a.id = :id",nativeQuery = true)
    Integer checkCanDeleteParentCategory(@Param("id") Integer id );
}