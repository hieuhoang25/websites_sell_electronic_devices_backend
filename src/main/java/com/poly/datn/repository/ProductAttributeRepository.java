package com.poly.datn.repository;

import com.poly.datn.entity.ProductAttribute;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.web.bind.annotation.RequestParam;


import javax.transaction.Transactional;
import java.util.List;

public interface ProductAttributeRepository extends JpaRepository<ProductAttribute, Integer> {
    List<ProductAttribute> findByProductId(Integer id);

    @Transactional
    @Modifying
    @Query(value = "DELETE FROM product_attribute where id in (:listId)",nativeQuery = true)
    void deleteByListId(@RequestParam("listId") List<Integer> listId);


}