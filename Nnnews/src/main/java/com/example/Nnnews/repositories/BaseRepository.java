package com.example.Nnnews.repositories;

import com.example.Nnnews.entities.BaseEntity;
import com.example.Nnnews.types.entityfieldtypes.ActivityTypeForLikes;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.UUID;

@NoRepositoryBean
public interface BaseRepository<T extends BaseEntity> extends JpaSpecificationExecutor<T>, CrudRepository<T, UUID> {

    Iterable<T> findAll();

    Page<T> findAll(Specification<T> specification, Pageable pageable);

    void deleteById(UUID id);

}
