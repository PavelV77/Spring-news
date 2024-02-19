package com.example.Nnnews.services.serviceInterfaces;

import com.example.Nnnews.dto.entitydto.CommentDto;
import com.example.Nnnews.dto.entitydto.NewsDto;
import com.example.Nnnews.dto.filterdto.PageSortFilterDto;
import com.example.Nnnews.entities.Comment;
import com.example.Nnnews.entities.News;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.UUID;

public interface CommentService extends BaseService<Comment> {
    Iterable<Comment> findByNewsId(UUID newsId);
    Comment updateEntity(UUID id, CommentDto dto);
    long count(UUID id);
}
