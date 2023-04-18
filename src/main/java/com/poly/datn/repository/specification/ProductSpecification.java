package com.poly.datn.repository.specification;

import com.poly.datn.common.SearchCriteria;
import com.poly.datn.common.constant.SearchOperation;
import com.poly.datn.entity.Category;
import com.poly.datn.entity.Product;
import com.poly.datn.entity.ProductVariant;
import com.poly.datn.repository.ProductRepository;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
public class ProductSpecification implements Specification<Product> {
    private List<SearchCriteria> list = new ArrayList<>();

    public void add(SearchCriteria criteria) {
        list.add(criteria);
    }

    private Predicate categoryAndSubCategory(Root<Product> root,
                                             CriteriaQuery<?> query,
                                             CriteriaBuilder criteriaBuilder,
                                             Integer id) {
        Subquery<Integer> subquery1 = query.subquery(Integer.class);
        Root<Category> subRoot1 = subquery1.from(Category.class);
        subquery1.select(subRoot1.get("id"))
                .where(criteriaBuilder.or(
                        criteriaBuilder.equal(subRoot1.get("id"), id),
                        criteriaBuilder.equal(subRoot1.get("parent").get("id"), id),
                        criteriaBuilder.equal(subRoot1.get("parent").get("parent").get("id"), id)
                ));
        return criteriaBuilder.and(root.get("category").get("id").in(subquery1));
    }

    private Predicate findByMultipleStorage(Root<Product> root,
                                            CriteriaQuery<?> query,
                                            CriteriaBuilder criteriaBuilder,
                                            List<String> values) {
        Join<Product, ProductVariant> productVariantJoin = root.join("productVariants");
        List<Integer> storageIds = values.stream().map(Integer::parseInt).collect(Collectors.toList());
        query.select(productVariantJoin.get("storage").get("id")).distinct(true);
        return criteriaBuilder.in(productVariantJoin.get("storage").get("id")).value(storageIds);
    }

    private Predicate findByMultipleBrand(Root<Product> root,
                                            CriteriaQuery<?> query,
                                            CriteriaBuilder criteriaBuilder,
                                            List<String> values) {
        List<Integer> brandIds = values.stream().map(Integer::parseInt).collect(Collectors.toList());
        return criteriaBuilder.in(root.get("brand").get("id")).value(brandIds);
    }

    private Predicate hasVariant(Root<Product> root,
                                          CriteriaQuery<?> query,
                                          CriteriaBuilder criteriaBuilder) {
        //list id of product
        Subquery<Integer> subquery = query.subquery(Integer.class);
        //product_id from productVariant
        Root<ProductVariant> variant = subquery.from(ProductVariant.class);
        //(select product_id from productVariant)
        subquery.select(variant.get("product").get("id"));
        Predicate variantExists = criteriaBuilder.equal(variant.get("product"), root);
        Predicate variantIsActive = criteriaBuilder.equal(variant.get("status"),true);
        //(select product_id from productVariant vr where status = true and vr.product_id = product.id )
        subquery.where(variantExists,variantIsActive);
        return criteriaBuilder.in(root.get("id")).value(subquery);
    }

    @Override
    public Predicate toPredicate(Root<Product> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        List<Predicate> predicates = new ArrayList<>();
        SearchCriteria isDeletedCriteria = new SearchCriteria("isDelete", false, SearchOperation.EQUAL);
        add(isDeletedCriteria);
        predicates.add(hasVariant(root,query,criteriaBuilder));
        for (SearchCriteria criteria : list) {
            switch (criteria.getOperation()) {
                case GREATER_THAN:
                    predicates.add(
                            criteriaBuilder.greaterThan(root.get(criteria.getKey()), criteria.getValue().toString()));
                    break;
                case LESS_THAN:
                    predicates
                            .add(criteriaBuilder.lessThan(root.get(criteria.getKey()), criteria.getValue().toString()));
                    break;
                case GREATER_THAN_EQUAL:
                    predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get(criteria.getKey()),
                            criteria.getValue().toString()));
                    break;
                case LESS_THAN_EQUAL:
                    predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get(criteria.getKey()),
                            criteria.getValue().toString()));
                    break;
                case LIKE:
                    predicates.add(criteriaBuilder.like(root.get(criteria.getKey()),
                            "%" + criteria.getValue().toString() + "%"));
                    break;
                case LIKE_START:
                    predicates.add(
                            criteriaBuilder.like(root.get(criteria.getKey()), "%" + criteria.getValue().toString()));
                    break;
                case LIKE_END:
                    predicates.add(
                            criteriaBuilder.like(root.get(criteria.getKey()), criteria.getValue().toString() + "%"));
                    break;
                case EQUAL:
                    if (criteria.getKey().equals("category")) {
                        predicates.add(categoryAndSubCategory(root, query, criteriaBuilder, Integer.parseInt(criteria.getValue().toString())));
                        break;
                    }
                    predicates.add(criteriaBuilder.equal(root.get(criteria.getKey()), criteria.getValue()));
                    break;
                case NOT_EQUAL:
                    predicates.add(criteriaBuilder.notEqual(root.get(criteria.getKey()), criteria.getValue()));
                    break;
                case IN:
                    List<String> values = Arrays.asList(criteria.getValue().toString().split(","));
                    if (criteria.getKey().equals("brand"))
                        predicates.add(findByMultipleBrand(root, query, criteriaBuilder, values));
                    else if (criteria.getKey().equals("storage"))
                        predicates.add(findByMultipleStorage(root, query, criteriaBuilder, values));
                    else
                        predicates.add(root.get(criteria.getKey()).in(values));
                    break;
                case NOT_IN:
                    predicates.add(criteriaBuilder.not(root.get(criteria.getKey())).in(criteria.getValue()));
                    break;
            }
        }

        return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
    }
}
