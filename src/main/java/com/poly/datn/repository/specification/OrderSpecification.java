package com.poly.datn.repository.specification;

import com.poly.datn.entity.Order;
import org.springframework.data.jpa.domain.Specification;

public class OrderSpecification {
    public Specification<Order> hasStatus(Integer statusId) {
        return statusId == 0 ? null :
                (root, query, criteriaBuilder) ->
                        criteriaBuilder.equal(root.get("status").get("id"), statusId);
    }

    public Specification<Order> hasUser(Integer userId) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("user").get("id"), userId);
    }

    public Specification<Order> getUserOrdered(Integer statusId, Integer userId) {
        if (hasStatus(statusId) != null)
            return Specification.where(hasStatus(statusId).and(hasUser(userId)));
        return hasUser(userId);
    }
}
