package com.poly.datn.repository;

import com.poly.datn.entity.Product;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Integer>, JpaSpecificationExecutor {

    @Modifying
    @Transactional
    @Query(value = "UPDATE product SET is_delete = :isDeleted WHERE id = :id LIMIT 1",nativeQuery = true)
    void deleteProduct(@Param("id") Integer id,@Param("isDeleted") Integer isDeleted);

    @Query(value = " select p.id, product_name,description,p.create_date,p.update_date," +
            "category_id,is_delete,brand_id,promotion_id,type,p.image,brand_name " +
            "category_name,pd.name as 'promotion_name' from product p left join brand b" +
            " on p.brand_id = b.id join category c on p.category_id = c.id " +
            "left join promotion_product pd on p.promotion_id = pd.id",nativeQuery = true)
    List<Product> findAllProductAndBrandName(Pageable pageable);

    @Query(value = "select count(*) from product",nativeQuery = true)
    Integer countProduct();

    @Query(value = "select * from product where id = :id",nativeQuery = true)
    Product findByProductId(Integer id);







}