package com.example.Nnnews.bus;

import com.example.Nnnews.dto.entitydto.LikeDto;
import com.example.Nnnews.dto.filterdto.PageSortFilterDto;
import com.example.Nnnews.entities.Like;
import com.example.Nnnews.services.serviceInterfaces.LikeService;
import com.example.Nnnews.types.entityfieldtypes.ActivityTypeForLikes;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Component
public class LikeBean {
    @Autowired
    private LikeService likeService;
    @Autowired
    private ModelMapper modelMapper;

    @Transactional
    public LikeDto createLike(LikeDto likeDto) {
        return modelMapper.map(likeService.save(modelMapper.map(likeDto, Like.class)), LikeDto.class);
    }

    @Transactional
    public void deleteById(UUID likeId) {
        likeService.deleteById(likeId);
    }

    @Transactional
    public long count(UUID id, ActivityTypeForLikes type) {
        return likeService.count(id, type);
    }

    @Transactional
    public LikeDto findById(UUID id) {
        return modelMapper.map(likeService.findById(id), LikeDto.class);
    }

    @Transactional
    public List<LikeDto> findWithPage(UUID newsId, ActivityTypeForLikes type, PageSortFilterDto dto) {
        Page<Like> page = likeService.findAllWithPageFilter(newsId, type, dto);
        List<LikeDto> listDto = new ArrayList<>();
        page.map(e -> {
            LikeDto likeDto = modelMapper.map(e, LikeDto.class);
            likeDto.setUserId(e.getUser().getId());
            return listDto.add(likeDto);
        });
        return listDto;
    }
}
