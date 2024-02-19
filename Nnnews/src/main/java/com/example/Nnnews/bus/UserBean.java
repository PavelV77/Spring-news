package com.example.Nnnews.bus;

import com.example.Nnnews.dto.entitydto.UserDto;
import com.example.Nnnews.dto.filterdto.PageSortFilterDto;
import com.example.Nnnews.entities.User;
import com.example.Nnnews.services.serviceInterfaces.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Component
public class UserBean {
    @Autowired
    private UserService userService;
    @Autowired
    private ModelMapper modelMapper;

    public UserDto saveUser(UserDto dto) {
        User user = modelMapper.map(dto, User.class);
        return modelMapper.map(userService.save(user), UserDto.class);
    }

    public UserDto findById(UUID id) {
        User user = userService.findById(id);
        return modelMapper.map(userService.findById(id), UserDto.class);
    }

    public List<UserDto> findAll() {
        Iterable<User> listUser = userService.findAll();
        List<UserDto> listDto = new ArrayList<>();
        listUser.forEach(e -> listDto.add(modelMapper.map(e, UserDto.class)));
        return listDto;
    }

    public UserDto updateEntity(UUID id, UserDto dto) {
        return modelMapper.map(userService.updateEntity(id, dto), UserDto.class);
    }

    public void deleteById(UUID id) {
        userService.deleteById(id);
    }

    public Page<UserDto> findAllWithPageFilter(PageSortFilterDto dto) {
        Page<User> all = userService.findAllWithPageFilter(dto);
        return all.map(e -> modelMapper.map(e, UserDto.class));
    }


}
