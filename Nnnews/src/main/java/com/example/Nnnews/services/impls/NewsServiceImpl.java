package com.example.Nnnews.services.impls;

import com.example.Nnnews.dto.entitydto.NewsDto;
import com.example.Nnnews.entities.News;
import com.example.Nnnews.entities.User;
import com.example.Nnnews.repositories.NewsRepository;
import com.example.Nnnews.services.serviceInterfaces.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class NewsServiceImpl extends BaseServiceImpl<News, NewsRepository> implements NewsService {

    private NewsRepository repository;
    @Autowired
    private UserServiceImpl userService;

    public NewsServiceImpl(NewsRepository repository) {
        super(repository);
        this.repository = repository;
    }

    @Override
    public News createNews(UUID userID, News news) {
        User user = userService.findById(userID);
        List<News> collection = user.getNewsCollection();
        collection.add(news);
        user.setNewsCollection(collection);
        news.setUser(userService.save(user));
        return repository.save(news);
    }


    public News updateEntity(UUID id, NewsDto dto) {
        News news = repository.findById(id).get();
        news.setHead(dto.getHead());
        news.setBody(dto.getBody());
        news.setStatus(dto.getStatus());
        return repository.save(news);
    }

    public void updateEntity(News news, NewsDto dto) {
        news.setHead(dto.getHead());
        news.setBody(dto.getBody());
        news.setStatus(dto.getStatus());
    }
}
