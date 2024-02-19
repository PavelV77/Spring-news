package com.example.Nnnews.filter;

import com.example.Nnnews.dto.filterdto.BaseFilterDto;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class SimpleFilterSpecification {
    public SimpleFilterSpecification() {
    }
    public Predicate getPredicateFromFilter(BaseFilterDto dto, Root root, CriteriaQuery query, CriteriaBuilder criteriaBuilder) {
        Predicate predicate = null;
        switch (dto.getType()) {
            case LIKE:
                predicate = criteriaBuilder.like(root.get(dto.getField()), "%" + dto.getValue() + "%");
                break;
            case EQUALS:
                predicate = criteriaBuilder.equal(root.get(dto.getField()), dto.getValue());
                break;
        }
        return predicate;
    }
}
