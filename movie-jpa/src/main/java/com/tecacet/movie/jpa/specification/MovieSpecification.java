package com.tecacet.movie.jpa.specification;

import com.tecacet.movie.jpa.model.EntityMovie;

import org.springframework.data.jpa.domain.Specification;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

public class MovieSpecification implements Specification<EntityMovie> {

    private final SearchCriteria criteria;

    public MovieSpecification(SearchCriteria criteria) {
        super();
        this.criteria = criteria;
    }

    @Override
    public Predicate toPredicate(Root<EntityMovie> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
        if (criteria.getOperation() == SearchCriteria.Operation.GREATER) {
            return builder.greaterThan(root.get(criteria.getKey()), criteria.getValue().toString());
        } else if (criteria.getOperation() == SearchCriteria.Operation.LESS) {
            return builder.lessThan(root.get(criteria.getKey()), criteria.getValue().toString());
        } else if (criteria.getOperation() == SearchCriteria.Operation.EQUAL) {
            return builder.equal(root.<String>get(criteria.getKey()), criteria.getValue().toString());
        } else {
            return builder.like(root.get(criteria.getKey()), "%" + criteria.getValue() + "%");
        }
    }

}
