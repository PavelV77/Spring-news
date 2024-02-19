package com.example.Nnnews.mappers;

import com.example.Nnnews.dto.entitydto.NewsDto;
import com.example.Nnnews.entities.News;
import org.mapstruct.*;
@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface NewsMapper {
    NewsDto toDto(News news);
    News toEntity(NewsDto dto);
}
