package com.example.Nnnews.controllers;

import com.example.Nnnews.bus.NewsBean;
import com.example.Nnnews.dto.entitydto.NewsDto;
import com.example.Nnnews.dto.filterdto.PageSortFilterDto;
import com.example.Nnnews.services.serviceInterfaces.NewsService;
import com.example.Nnnews.services.serviceInterfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/news")
public class NewsController {
    @Autowired
    private NewsBean newsBean;

    @PostMapping("/all")
    public List<NewsDto> getAll(@RequestBody PageSortFilterDto dto) {
        return newsBean.findAllWithPageFilter(dto);
    }

    @GetMapping("/{newsId}")
    public NewsDto getNews(@PathVariable("newsId") UUID id) {
        return newsBean.getNewsById(id);
    }

    // TODO: 13.02.2024 Spring Security userId!
    @PostMapping
    public NewsDto addNews(@RequestBody NewsDto newsDto) {
        newsDto.setUserId(UUID.fromString("01a7e62c-cbfa-4049-b93c-af868ec1d494"));
        return newsBean.createNews(newsDto);
    }

    @DeleteMapping("/{newsId}")
    public void deleteNews(@PathVariable("newsId") UUID newsId) {
        newsBean.deleteById(newsId);
    }

    @PutMapping
    public NewsDto putNews(@RequestBody NewsDto newsDto) {
        newsBean.updateNews(newsDto);
        return newsBean.getNewsById(newsDto.getId());
    }


}
