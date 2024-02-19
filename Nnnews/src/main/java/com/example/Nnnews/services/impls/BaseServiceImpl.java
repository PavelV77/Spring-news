package com.example.Nnnews.services.impls;

import com.example.Nnnews.dto.filterdto.PageSortFilterDto;
import com.example.Nnnews.dto.filterdto.PageDto;
import com.example.Nnnews.dto.filterdto.SortDto;
import com.example.Nnnews.dto.filterdto.BaseFilterDto;
import com.example.Nnnews.dto.filterdto.FilterCollectionDto;
import com.example.Nnnews.entities.BaseEntity;
import com.example.Nnnews.entities.News;
import com.example.Nnnews.errors.BusinessException;
import com.example.Nnnews.errors.SortException;
import com.example.Nnnews.repositories.BaseRepository;
import com.example.Nnnews.types.entityfieldtypes.ActivityTypeForLikes;
import com.example.Nnnews.types.filtertypes.SimpleFilterType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

import javax.persistence.criteria.Predicate;
import java.util.*;


@Service
public abstract class BaseServiceImpl<T extends BaseEntity, R extends BaseRepository<T>> {
    private R repository;

    @Autowired
    public BaseServiceImpl(R repository) {
        this.repository = repository;
    }

    public long count() {
        return repository.count();
    }

    public T findById(UUID id) {
        return repository.findById(id).get();
    }

    public Iterable<T> findAll() {
        return repository.findAll();
    }

    public Iterable<T> saveAll(Iterable<T> entities) {
        return repository.saveAll(entities);
    }

    public void deleteAll() {
        repository.deleteAll();
    }

    public T save(T entity) {
        return repository.save(entity);
    }

    public void deleteById(UUID id) {
        repository.deleteById(id);
    }

    public T createEntity(T entity) {
        return repository.save(entity);
    }


    //Нет масштабируемости, нет проверки филдов(она осуществляется именно на уровне сервиса)
    //Нет проверки (или дефолт значения?) pageableDto

    public void checkField(String field){
        try {
            System.out.println(this.getClass().getNestMembers());
            getClass().getGenericSuperclass().getClass().getDeclaredField(field);
        } catch (NoSuchFieldException e) {
            throw new BusinessException("ERROR: "+field+ " not found", HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }
    public Specification<T> getSpecification(FilterCollectionDto filterCollection){
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicateList =new ArrayList<>();
            for (BaseFilterDto baseFilterDto : filterCollection.getBaseFilterDtoList()) {
                try {
                    SimpleFilterType.valueOf(baseFilterDto.getType().name());
                } catch (IllegalArgumentException e) {
                    throw new RuntimeException(new SortException("Error field in filterDto!"));
                }
                switch (baseFilterDto.getType()) {
                    case LIKE:
                        predicateList.add(criteriaBuilder.like(root.get(baseFilterDto.getField()), "%" + baseFilterDto.getValue() + "%"));
                        break;
                    case EQUALS:
                        predicateList.add(criteriaBuilder.equal(root.get(baseFilterDto.getField()), baseFilterDto.getValue()));
                        break;
                }
            }
            Predicate predicate = switch (filterCollection.getOperationBetweenPredicate()) {
                case AND -> criteriaBuilder.and(predicateList.toArray(new Predicate[0]));
                case OR -> criteriaBuilder.or(predicateList.toArray(new Predicate[0]));
            };
            return predicate;
        };
    }

    public Page<T> findAllWithPageFilter(PageSortFilterDto dto) {
        PageDto pageableDto = dto.getPage();
        SortDto sortDto = dto.getSort();
        return repository.findAll(getSpecification(dto.getFilterCollection()),
                PageRequest.of(pageableDto.getPage(), pageableDto.getPageSize(),
                        Sort.by(Sort.Direction.fromString(sortDto.getSortDirection()), sortDto.getSortField())));
    }



}
