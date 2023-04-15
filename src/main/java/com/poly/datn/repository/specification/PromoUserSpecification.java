package com.poly.datn.repository.specification;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.Predicate;

import org.springframework.data.jpa.domain.Specification;

import com.poly.datn.entity.PromotionUser;

public class PromoUserSpecification {

    public Specification<PromotionUser> isNotDeleted() {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("isDeleted"), false);
    }

    public Specification<PromotionUser> getByCodeAndUserId(String code, Integer userId) {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> list = new ArrayList<>();
            list.add(criteriaBuilder.equal(root.get("promotionCode"), code));
            list.add(criteriaBuilder.equal(root.get("isDeleted"), false));
            return criteriaBuilder.and(list.toArray(new Predicate[0]));
        };
    }

    public Specification<PromotionUser> ofUser(Integer userId) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("user").get("id"), userId);
    }

    public Specification<PromotionUser> isNotUsed() {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("isUsed"), false);
    }

    public Specification<PromotionUser> betweenTime(Instant from, Integer userId) {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> list = new ArrayList<>();
            // list.add(criteriaBuilder.lessThanOrEqualTo(root.get("startDate"), from));
            // list.add(criteriaBuilder.greaterThanOrEqualTo(root.get("expirationDate"),from));
            list.add(
                    criteriaBuilder.and(criteriaBuilder.lessThanOrEqualTo(root.get("startDate"), from),
                            criteriaBuilder.greaterThanOrEqualTo(root.get("expirationDate"), from)));

            // criteriaBuilder.greaterThanOrEqualTo(root.get("expirationDate"),from),
            list.add(criteriaBuilder.equal(root.get("user").get("id"), userId));

            // list.add(criteriaBuilder.equal(root.get("user").get("id"), userId));
            return criteriaBuilder.and(list.toArray(new Predicate[0]));
        };
    }

    public Specification<PromotionUser> getValidPromo(Instant now, Integer userId) {
        return Specification.where(betweenTime(now, userId).and(isNotDeleted()).and(isNotUsed()));
    }

    public Specification<PromotionUser> checkMinimumAmount(Double total) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.or(
                criteriaBuilder.isNull(root.get("promotionType").get("conditionMinimum")),
                criteriaBuilder.lessThanOrEqualTo(root.get("promotionType").get("conditionMinimum"), total));
    }

    public Specification<PromotionUser> getValidPromoForOrder(Instant now, Double total, Integer userId) {
        return Specification
                .where(betweenTime(now, userId).and(isNotDeleted()).and(isNotUsed()).and(checkMinimumAmount(total)));
    }

}
