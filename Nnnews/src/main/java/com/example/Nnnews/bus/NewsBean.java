package com.example.Nnnews.bus;

import com.example.Nnnews.dto.entitydto.NewsDto;
import com.example.Nnnews.dto.filterdto.PageSortFilterDto;
import com.example.Nnnews.entities.Like;
import com.example.Nnnews.entities.News;
import com.example.Nnnews.errors.BusinessException;
import com.example.Nnnews.mappers.NewsMapper;
import com.example.Nnnews.services.serviceInterfaces.LikeService;
import com.example.Nnnews.services.serviceInterfaces.NewsService;
import com.example.Nnnews.types.entityfieldtypes.ActivityTypeForLikes;
import com.example.Nnnews.types.entityfieldtypes.StatusTypeNews;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.swing.text.html.parser.Entity;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


@Component
public class NewsBean {
    @Autowired
    private NewsService newsService;
    @Autowired
    private LikeService likeService;
    @Autowired
    private NewsMapper newsMapper;
    @Autowired
    private ModelMapper modelMapper;

    public NewsDto createNews(NewsDto newsDto) {
        return modelMapper.map(newsService.save(modelMapper.map(newsDto, News.class)), NewsDto.class);
    }

    /*править контроллер, маппер,
     * в news добавить enum type status - если статус "опубликовано" то запретить update и выкинуть 422err,
     * Transactional,
     * mapper,
     * отдельный контроллер для получения списка лайков
     * */

    private void validateNewsDto(NewsDto newsDto){
        if (newsDto.getHead() == null || newsDto.getHead().length()>65535)
            throw new BusinessException("ERROR: Head is null", HttpStatus.UNPROCESSABLE_ENTITY);
        else if (newsDto.getBody() == null) {
            throw new BusinessException("ERROR: Body is null", HttpStatus.UNPROCESSABLE_ENTITY);
        }
        else if (newsDto.getStatus() == null)
            throw new BusinessException("ERROR: Status is null", HttpStatus.UNPROCESSABLE_ENTITY);
    }
    @Transactional
    public void updateNews(NewsDto newsDto) {
        News news = newsService.findById(newsDto.getId());
        if (news.getStatus() == StatusTypeNews.PUBLISHED) {
            throw new BusinessException("ERROR: Can't update PUBLISHED news", HttpStatus.UNPROCESSABLE_ENTITY);
        }
        validateNewsDto(newsDto);
        news.setHead(newsDto.getHead());
        news.setBody(newsDto.getBody());
        news.setStatus(newsDto.getStatus());
    }

    @Transactional
    public NewsDto getNewsById(UUID id) {
        return modelMapper.map(newsService.findById(id), NewsDto.class);
    }

    @Transactional
    public List<NewsDto> findAllWithPageFilter(PageSortFilterDto dto) {
        List<NewsDto> newsDtoList = new ArrayList<>();
        newsService.findAllWithPageFilter(dto).getContent().forEach(e -> {
            NewsDto newsDto = modelMapper.map(e,NewsDto.class);
            newsDtoList.add(newsDto);
        });
        return newsDtoList;
    }

    @Transactional
    public void deleteById(UUID newsId) {
        newsService.deleteById(newsId);
    }
}
