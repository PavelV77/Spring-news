package com.example.Nnnews.services.impls;

import com.example.Nnnews.dto.entitydto.CommentDto;
import com.example.Nnnews.entities.Comment;
import com.example.Nnnews.entities.News;
import com.example.Nnnews.entities.User;
import com.example.Nnnews.repositories.CommentRepository;
import com.example.Nnnews.services.serviceInterfaces.CommentService;
import com.example.Nnnews.services.serviceInterfaces.NewsService;
import com.example.Nnnews.services.serviceInterfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;


@Service
public class CommentServiceImpl extends BaseServiceImpl<Comment, CommentRepository> implements CommentService{
    CommentRepository repository;
    @Autowired
    private NewsService newsService;
    @Autowired
    private UserService userService;
    public CommentServiceImpl(CommentRepository repository) {
        super(repository);
        this.repository = repository;
    }
    @Override
    public Iterable<Comment> findByNewsId(UUID newsId) {
        return repository.findByNewsId(newsId);
    }

    @Override
    public Comment updateEntity(UUID id, CommentDto dto) {
        Comment comment = repository.findById(id).get();
        comment.setBody(dto.getBody());
        return repository.save(comment);
    }

    @Override
    public long count(UUID id) {
        return repository.countByNewsId(id);
    }
}
