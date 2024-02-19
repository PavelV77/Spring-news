package com.example.Nnnews.controllers;

import com.example.Nnnews.bus.LikeBean;
import com.example.Nnnews.dto.entitydto.LikeDto;
import com.example.Nnnews.services.serviceInterfaces.LikeService;
import com.example.Nnnews.types.entityfieldtypes.ActivityTypeForLikes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/news")
public class LikeController {
    @Autowired
    private LikeBean likeBean;

    @GetMapping("/{newsId}/activities/{type}")
    public long getLikeCount(@PathVariable("newsId") UUID newsId, @PathVariable("type") ActivityTypeForLikes type){
        return likeBean.count(newsId, type);
    }
    @PostMapping("/activities")
    public LikeDto addLike(@RequestBody LikeDto likeDto){
        return likeBean.createLike(likeDto);
    }

    @DeleteMapping("/activities/{likeId}")
    public void deleteLike(@PathVariable("likeId") UUID likeId){
        likeBean.deleteById(likeId);
    }
}
