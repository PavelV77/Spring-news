package com.example.Nnnews.services.serviceInterfaces;

import com.example.Nnnews.dto.entitydto.NewsDto;
import com.example.Nnnews.dto.entitydto.UserDto;
import com.example.Nnnews.entities.News;
import com.example.Nnnews.entities.User;

import java.util.UUID;

public interface UserService extends BaseService<User> {
     User createUser(String login);
     User findByLogin(String login);
     User updateEntity(UUID id, UserDto dto);
}
