package com.poly.datn.repository.specification;

import com.poly.datn.common.SearchCriteria;
import com.poly.datn.entity.Category;
import com.poly.datn.entity.Product;

import lombok.Data;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;

@Data
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
                        criteriaBuilder.equal(subRoot1.get("id"),id),
                        criteriaBuilder.equal(subRoot1.get("parent").get("id"),id),
                        criteriaBuilder.equal(subRoot1.get("parent").get("parent").get("id"),id)
                ));
        return criteriaBuilder.and(root.get("category").get("id").in(subquery1));


    }

    @Override
    public Predicate toPredicate(Root<Product> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        List<Predicate> predicates = new ArrayList<>();

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
                    if(criteria.getKey().equals("category")){
                        predicates.add(categoryAndSubCategory(root,query,criteriaBuilder,Integer.parseInt(criteria.getValue().toString())));
                        break;
                    }
                    predicates.add(criteriaBuilder.equal(root.get(criteria.getKey()), criteria.getValue()));
                    break;
                case NOT_EQUAL:
                    predicates.add(criteriaBuilder.notEqual(root.get(criteria.getKey()), criteria.getValue()));
                    break;
                case IN:
                    predicates.add(criteriaBuilder.in(root.get(criteria.getKey())).value(criteria.getValue()));
                    break;
                case NOT_IN:
                    predicates.add(criteriaBuilder.not(root.get(criteria.getKey())).in(criteria.getValue()));
                    break;
            }
        }
        return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
    }
}
