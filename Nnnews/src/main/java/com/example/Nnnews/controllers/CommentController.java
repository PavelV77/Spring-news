package com.example.Nnnews.controllers;

import com.example.Nnnews.bus.CommentBean;
import com.example.Nnnews.dto.entitydto.CommentDto;
import com.example.Nnnews.services.serviceInterfaces.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/news")
public class CommentController {
    @Autowired
    private CommentService commentService;
    @Autowired
    private CommentBean commentBean;

    @GetMapping("/{newsId}/comments")
    public long getCount(@PathVariable UUID newsId){
        return commentBean.count(newsId);
    }

    // TODO: 14.02.2024 Spring Security userId
    @PostMapping("/comments/{userId}")
    public CommentDto addComment(@PathVariable("userId") UUID userId,@RequestBody CommentDto commentDto){
        commentDto.setUserID(userId);
        return commentBean.createComment(commentDto);
    }
    @DeleteMapping("/comments/{commentId}")
    public void deleteComment(@PathVariable("commentId") UUID commentId){
        commentService.deleteById(commentId);
    }

    @PutMapping("/comments")
    public CommentDto putComment(@RequestBody CommentDto commentDto) {
        commentBean.updateEntity(commentBean.findById(commentDto.getId()), commentDto);
        return commentBean.findById(commentDto.getId());
    }
}
