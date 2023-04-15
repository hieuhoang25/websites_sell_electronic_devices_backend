package com.poly.datn.repository;

import com.poly.datn.entity.Brand;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface BrandRepository extends JpaRepository<Brand, Integer> {
          @Query(value = "select count(*) from brand b join product p on b.id = p.brand_id "+
          "where b.id = :id",nativeQuery = true)
          Integer checkCanDelete(@Param("id") Integer id );
}
