package com.example.Nnnews.controllers;

import com.example.Nnnews.bus.LikeBean;
import com.example.Nnnews.dto.entitydto.CommentDto;
import com.example.Nnnews.dto.entitydto.LikeDto;
import com.example.Nnnews.dto.filterdto.PageSortFilterDto;
import com.example.Nnnews.types.entityfieldtypes.ActivityTypeForLikes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/news")
public class LikeGetListController {

    @Autowired
    private LikeBean likeBean;

    @PostMapping("/{newsId}/activities/{activityType}")
    public List<LikeDto> getLikeList(@PathVariable UUID newsId, @PathVariable ActivityTypeForLikes activityType, @RequestBody PageSortFilterDto dto){
        return likeBean.findWithPage(newsId, activityType, dto);
    }
}
