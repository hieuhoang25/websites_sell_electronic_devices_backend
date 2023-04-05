package com.poly.datn.repository;

import com.poly.datn.entity.Product;
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
    
    @Query(value = " select p.id, product_name,description,p.create_date,p.update_date," +
            "category_id,is_delete,brand_id,promotion_id,type,p.image,brand_name, " +
            "category_name,pd.name as 'promotion_name' from product p left join brand b" +
            " on p.brand_id = b.id join category c on p.category_id = c.id " +
            "left join promotion_product pd on p.promotion_id = pd.id  where brand_name like :keysearch or " +
            "category_name like :keysearch or product_name like :keysearch or pd.name like :keysearch",nativeQuery = true)
    List<Product> findAllByFilter(Pageable pageable,@Param("keysearch")String keysearch);

    @Query(value = "select count(*) from product p left join brand b" +
            " on p.brand_id = b.id join category c on p.category_id = c.id " +
            "left join promotion_product pd on p.promotion_id = pd.id  where brand_name like :keysearch or " +
            "category_name like :keysearch or product_name like :keysearch or pd.name like :keysearch ",nativeQuery = true)
    Integer countProductByFilter(@Param("keysearch")String keysearch);

    @Query(value = " select p.id, product_name,description,p.create_date,p.update_date," +
            "category_id,is_delete,brand_id,promotion_id,type,p.image,brand_name, " +
            "category_name,pd.name as 'promotion_name' from product p left join brand b" +
            " on p.brand_id = b.id join category c on p.category_id = c.id " +
            "left join promotion_product pd on p.promotion_id = pd.id  where brand_name like :keysearch " +
            "and is_delete = :isDeleted or category_name like :keysearch and is_delete = :isDeleted or product_name " +
            "like :keysearch and is_delete = :isDeleted or pd.name like :keysearch and is_delete = :isDeleted ",nativeQuery = true)
    List<Product> findAllByFilterWithDeleted(Pageable pageable,@Param("keysearch")String keysearch,@Param("isDeleted") Integer isDeleted);


    @Query(value = "select count(*) from product p left join brand b " +
            "on p.brand_id = b.id join category c on p.category_id = c.id "+
            "left join promotion_product pd on p.promotion_id = pd.id  where brand_name like :keysearch " +
            "and is_delete = :isDeleted or category_name like :keysearch and is_delete = :isDeleted or product_name " +
            "like :keysearch and is_delete = :isDeleted or pd.name like :keysearch and is_delete = :isDeleted",nativeQuery = true)
    Integer countProductFilterWithDeleted(@Param("keysearch")String keysearch,@Param("isDeleted") Integer isDeleted);
    @Query(value = "select p.id, p.product_name,p.description, " +
            "p.category_id, p.brand_id, p.promotion_id, p.image, p.create_date," +
            " p.update_date, p.is_delete, p.type " +
            "from product p left JOIN promotion_product pm on p.promotion_id = pm.id " +
            " where p.is_delete = false ORDER BY pm.discount desc LIMIT 10",nativeQuery = true)
    List<Product> findByTopSales();
    @Query(value = "select p.id, p.product_name,p.description, " +
            " p.category_id, p.brand_id, p.promotion_id, p.image, p.create_date," +
            " p.update_date, p.is_delete, p.type  from product p " +
            "where p.is_delete = false order by p.create_date desc limit 10", nativeQuery = true)
    List<Product> findByNewArrival();
}