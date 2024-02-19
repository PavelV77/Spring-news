package com.example.Nnnews.services.impls;

import com.example.Nnnews.dto.entitydto.LikeDto;
import com.example.Nnnews.dto.filterdto.*;
import com.example.Nnnews.entities.Like;
import com.example.Nnnews.entities.News;
import com.example.Nnnews.entities.User;
import com.example.Nnnews.errors.SortException;
import com.example.Nnnews.repositories.LikeRepository;
import com.example.Nnnews.repositories.NewsRepository;
import com.example.Nnnews.repositories.UserRepository;
import com.example.Nnnews.services.serviceInterfaces.LikeService;
import com.example.Nnnews.types.entityfieldtypes.ActivityTypeForLikes;
import com.example.Nnnews.types.filtertypes.SimpleFilterType;
import org.hibernate.annotations.ColumnTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static com.example.Nnnews.types.entityfieldtypes.ActivityTypeForLikes.DISLIKE;
import static com.example.Nnnews.types.entityfieldtypes.ActivityTypeForLikes.LIKE;

@Service
public class LikeServiceImpl extends BaseServiceImpl<Like, LikeRepository> implements LikeService {
    LikeRepository repository;
    @Autowired
    NewsRepository newsRepository;
    @Autowired
    UserRepository userRepository;

    public LikeServiceImpl(LikeRepository repository) {
        super(repository);
        this.repository = repository;
    }

    public long count(UUID id, ActivityTypeForLikes type) {
        return repository.countByNewsIdAndTypeOfActivity(id, type.name());
    }

    public Page<Like> findAllWithPageFilter(UUID newsId, ActivityTypeForLikes activityType, PageSortFilterDto dto) {

        Specification<Like> specification = ((root, query, criteriaBuilder) ->{
            News news = new News();
            news.setId(newsId);
            return criteriaBuilder.and(
                    criteriaBuilder.equal(root.get("news"), news),
                    criteriaBuilder.equal(root.get("typeOfActivity").as(String.class), activityType.name()));
        });
        PageDto pageableDto = dto.getPage();
        SortDto sortDto = dto.getSort();
        return repository.findAll(
                getSpecification(dto.getFilterCollection()).and(specification),
                PageRequest.of(
                        pageableDto.getPage(),
                        pageableDto.getPageSize(),
                        Sort.by(Sort.Direction.fromString(sortDto.getSortDirection()), sortDto.getSortField())));
    }
}
