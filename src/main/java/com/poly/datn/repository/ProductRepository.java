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
    @Query(value = "UPDATE product SET is_delete = :isDeleted WHERE id = :id LIMIT 1", nativeQuery = true)
    void deleteProduct(@Param("id") Integer id, @Param("isDeleted") Integer isDeleted);

    // Method 1
    @Query("SELECT p.id, p.productName, p.description, p.createDate, p.updateDate, " +
            "p.category.id, p.isDelete, p.brand.id, p.promotion.id, p.type, p.image, " +
            "p.brand.brandName, p.category.categoryName, p.promotion.name AS promotionName " +
            "FROM Product p LEFT JOIN p.brand LEFT JOIN p.category " +
            "LEFT JOIN p.promotion LEFT JOIN p.promotion.name AS promotionName")
    List<Product> findAllProductAndBrandName(Pageable pageable);

    // Method 2
    @Query("SELECT COUNT(p.id) FROM Product p")
    Integer countProduct();

    // Method 3
    @Query("SELECT p FROM Product p WHERE p.id = :id")
    Product findByProductId(Integer id);

    // Method 4
    @Query("SELECT p FROM Product p LEFT JOIN FETCH p.brand LEFT JOIN FETCH p.category " +
            "LEFT JOIN FETCH p.promotion WHERE p.brand.brandName LIKE :keysearch " +
            "OR p.category.categoryName LIKE :keysearch " +
            "OR p.productName LIKE :keysearch " +
            "OR p.promotion.name LIKE :keysearch")
    List<Product> findAllByFilter(Pageable pageable, @Param("keysearch") String keysearch);

    // Method 5
    @Query("SELECT COUNT(p.id) FROM Product p LEFT JOIN p.brand LEFT JOIN p.category " +
            "LEFT JOIN p.promotion WHERE p.brand.brandName LIKE :keysearch " +
            "OR p.category.categoryName LIKE :keysearch " +
            "OR p.productName LIKE :keysearch " +
            "OR p.promotion.name LIKE :keysearch")
    Integer countProductByFilter(@Param("keysearch") String keysearch);

    // Method 6
    @Query("SELECT p FROM Product p LEFT JOIN FETCH p.brand LEFT JOIN FETCH p.category " +
            "LEFT JOIN FETCH p.promotion WHERE (p.brand.brandName LIKE :keysearch " +
            "OR p.category.categoryName LIKE :keysearch " +
            "OR p.productName LIKE :keysearch " +
            "OR p.promotion.name LIKE :keysearch) " +
            "AND p.isDelete = :isDeleted")
    List<Product> findAllByFilterWithDeleted(Pageable pageable,
                                             @Param("keysearch") String keysearch, @Param("isDeleted") Integer isDeleted);

    // Method 7
    @Query("SELECT COUNT(p.id) FROM Product p LEFT JOIN p.brand LEFT JOIN p.category " +
            "LEFT JOIN p.promotion WHERE (p.brand.brandName LIKE :keysearch " +
            "OR p.category.categoryName LIKE :keysearch " +
            "OR p.productName LIKE :keysearch " +
            "OR p.promotion.name LIKE :keysearch) " +
            "AND p.isDelete = :isDeleted")
    Integer countProductFilterWithDeleted(@Param("keysearch") String keysearch,
                                          @Param("isDeleted") Integer isDeleted);


    @Query("SELECT p FROM Product p LEFT JOIN p.promotion pm " +
            "WHERE p.isDelete = false and p.promotion is not null and p.promotion.expirationDate is null " +
            "AND p.id IN (SELECT vr.product.id FROM ProductVariant vr)" +
            "ORDER BY pm.discountAmount DESC")
    List<Product> findByBigDiscount();

    @Query("SELECT p FROM Product p WHERE p.isDelete = false " +
            "AND p.id IN (SELECT vr.product.id FROM ProductVariant vr)" +
            "ORDER BY p.createDate DESC")
    List<Product> findByNewArrival();

    @Query("SELECT p " +
            "FROM OrderDetail od " +
            "JOIN od.productVariant pr " +
            "JOIN pr.product p " +
            "WHERE p.isDelete = false " +
            "AND p.id IN (SELECT vr.product.id FROM ProductVariant vr)" +
            "GROUP BY p.id " +
            "ORDER BY COUNT(pr.product.id) DESC ")
    List<Product> findByTopSales();
}