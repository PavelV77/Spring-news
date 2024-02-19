package com.example.Nnnews.services.serviceInterfaces;

import com.example.Nnnews.dto.entitydto.LikeDto;
import com.example.Nnnews.dto.entitydto.NewsDto;
import com.example.Nnnews.dto.filterdto.PageSortFilterDto;
import com.example.Nnnews.entities.Like;
import com.example.Nnnews.entities.News;
import com.example.Nnnews.types.entityfieldtypes.ActivityTypeForLikes;
import org.springframework.data.domain.Page;

import java.util.UUID;

public interface LikeService extends BaseService<Like> {
    long count(UUID id, ActivityTypeForLikes type);
    Page<Like> findAllWithPageFilter(UUID newsId, ActivityTypeForLikes activityType, PageSortFilterDto dto);
}
