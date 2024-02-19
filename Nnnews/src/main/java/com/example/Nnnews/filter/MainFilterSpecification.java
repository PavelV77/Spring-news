package com.example.Nnnews.filter;

import com.example.Nnnews.dto.filterdto.FilterCollectionDto;
import com.example.Nnnews.entities.BaseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

@Component
public class MainFilterSpecification<T extends BaseEntity> implements Specification<T> {
    List<FilterCollectionDto> filterCollectionList;
    @Autowired
    private AndFilterSpecification andFilter;
    @Autowired
    private OrFilterSpecification orFilter;
    public void setFilterCollectionList(List<FilterCollectionDto> filterCollectionList) {
        this.filterCollectionList = filterCollectionList;
    }
    @Override
    public Predicate toPredicate(Root root, CriteriaQuery query, CriteriaBuilder criteriaBuilder) {
        List<Predicate> specificationList = new ArrayList<>();
        for (FilterCollectionDto filterCollection : filterCollectionList) {
            switch (filterCollection.getOperationBetweenPredicate()) {
                case AND:
                    andFilter.setFilterDtoList(filterCollection.getBaseFilterDtoList());
                    specificationList.add(andFilter.getPredicate(root,query,criteriaBuilder));
                    break;
                case OR:
                    orFilter.setFilterDtoList(filterCollection.getBaseFilterDtoList());
                    specificationList.add(orFilter.getPredicate(root,query,criteriaBuilder));
                    break;
            }
        }
        return orFilter.getPredicateFromPredicates(specificationList,root,query,criteriaBuilder);
    }
}
