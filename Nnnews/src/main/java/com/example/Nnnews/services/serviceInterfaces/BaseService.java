package com.example.Nnnews.services.serviceInterfaces;

import com.example.Nnnews.dto.filterdto.PageSortFilterDto;
import com.example.Nnnews.entities.BaseEntity;
import com.example.Nnnews.entities.News;
import com.example.Nnnews.types.entityfieldtypes.ActivityTypeForLikes;
import org.springframework.data.domain.Page;

import java.util.Optional;
import java.util.UUID;

public interface BaseService<T extends BaseEntity> {
    T findById(UUID id);
    Iterable<T> findAll();
    void deleteAll();
    void deleteById(UUID id);
    T save(T entity);
    Page<T> findAllWithPageFilter(PageSortFilterDto dto);

}
