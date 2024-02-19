package com.example.Nnnews.services.serviceInterfaces;

import com.example.Nnnews.dto.entitydto.NewsDto;
import com.example.Nnnews.entities.News;
import com.example.Nnnews.entities.User;

import java.util.UUID;

public interface NewsService extends BaseService<News> {
    News createNews(UUID userId, News news);
    News updateEntity(UUID id, NewsDto dto);
    void updateEntity(News news, NewsDto dto);
}
