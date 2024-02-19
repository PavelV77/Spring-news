package com.example.Nnnews.bus;

import com.example.Nnnews.dto.entitydto.CommentDto;
import com.example.Nnnews.entities.Comment;
import com.example.Nnnews.services.serviceInterfaces.CommentService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Component
public class CommentBean {
    @Autowired
    private CommentService commentService;

    @Autowired
    private ModelMapper modelMapper;

    @Transactional
    public CommentDto createComment(CommentDto commentDto){
        return modelMapper.map(commentService.save(modelMapper.map(commentDto,Comment.class)),CommentDto.class);
    }

    @Transactional
    public CommentDto findById(UUID id){
        return modelMapper.map(commentService.findById(id),CommentDto.class);
    }

    public List<CommentDto> findByNewsId(UUID newsId){
        List<CommentDto> listDto = new ArrayList<>();
        List<Comment> listComment = (List<Comment>) commentService.findByNewsId(newsId);
        listComment.forEach(e -> listDto.add(modelMapper.map(e,CommentDto.class)));
        return listDto;
    }

    @Transactional
    public void updateEntity(CommentDto oldCommentDto, CommentDto commentDto) {
        oldCommentDto.setBody(commentDto.getBody());
        commentService.save(modelMapper.map(oldCommentDto,Comment.class));
    }

    @Transactional
    public long count(UUID newsId) {
        return commentService.count(newsId);
    }
}
