package com.example.Nnnews.filter;

import com.example.Nnnews.dto.filterdto.BaseFilterDto;
import org.springframework.stereotype.Component;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

@Component
public class OrFilterSpecification extends SimpleFilterSpecification {
    private List<BaseFilterDto> filterDtoList;
    public void setFilterDtoList(List<BaseFilterDto> filterDtoList) {
        this.filterDtoList = filterDtoList;
    }
    public Predicate getPredicate(Root root, CriteriaQuery query, CriteriaBuilder criteriaBuilder) {
        List<Predicate> filterPredicateList = new ArrayList<>();
        for (BaseFilterDto dto : filterDtoList)
            filterPredicateList.add(getPredicateFromFilter(dto, root, query, criteriaBuilder));
        return criteriaBuilder.or(filterPredicateList.toArray(new Predicate[0]));
    }
    public Predicate getPredicateFromPredicates(List<Predicate> predicateList, Root root, CriteriaQuery query, CriteriaBuilder criteriaBuilder) {
        return criteriaBuilder.or(predicateList.toArray(new Predicate[0]));
    }
}
