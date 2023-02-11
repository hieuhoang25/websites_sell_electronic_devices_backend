package com.poly.datn.repository.specification;

import com.poly.datn.common.SearchCriteria;
import com.poly.datn.entity.Product;
import lombok.Data;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;


@Data
public class ProductSpecification implements Specification<Product> {

    private List<SearchCriteria> list =  new ArrayList<>();

    public void add(SearchCriteria criteria){
        list.add(criteria);
    }
    @Override
    public Predicate toPredicate(Root<Product> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        List<Predicate> predicates = new ArrayList<>();

        for (SearchCriteria criteria:
                list) {
            switch(criteria.getOperation()) {
                case GREATER_THAN:
                    predicates.add(criteriaBuilder.greaterThan(root.get(criteria.getKey()), criteria.getValue().toString()));
                    break;
                case LESS_THAN:
                    predicates.add(criteriaBuilder.lessThan(root.get(criteria.getKey()), criteria.getValue().toString()));
                    break;
                case GREATER_THAN_EQUAL:
                    predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get(criteria.getKey()), criteria.getValue().toString()));
                    break;
                case LESS_THAN_EQUAL:
                    predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get(criteria.getKey()), criteria.getValue().toString()));
                    break;
                case LIKE:
                    predicates.add(criteriaBuilder.like(root.get(criteria.getKey()), "%" + criteria.getValue().toString() + "%"));
                    break;
                case LIKE_START:
                    predicates.add(criteriaBuilder.like(root.get(criteria.getKey()), "%" + criteria.getValue().toString()));
                    break;
                case LIKE_END:
                    predicates.add(criteriaBuilder.like(root.get(criteria.getKey()), criteria.getValue().toString() + "%"));
                    break;
                case EQUAL:
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
