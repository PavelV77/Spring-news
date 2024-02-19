package com.example.Nnnews.repositories;

import com.example.Nnnews.entities.Comment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CommentRepository extends CrudRepository<Comment,UUID>, BaseRepository<Comment> {
    Iterable<Comment> findByNewsId(UUID newsId);

    long countByNewsId(UUID newsId);
}
