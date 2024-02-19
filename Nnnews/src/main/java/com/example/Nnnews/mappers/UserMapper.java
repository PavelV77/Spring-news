package com.example.Nnnews.mappers;

import com.example.Nnnews.dto.entitydto.UserDto;
import com.example.Nnnews.entities.User;
import org.mapstruct.Mapper;

@Mapper
public interface UserMapper {
    UserDto toDto(User user);
}
