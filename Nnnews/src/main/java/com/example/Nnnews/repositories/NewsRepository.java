package com.example.Nnnews.repositories;

import com.example.Nnnews.entities.News;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface NewsRepository extends CrudRepository<News, UUID>, BaseRepository<News> {

}
