package com.example.Nnnews.controllers;

import com.example.Nnnews.bus.UserBean;
import com.example.Nnnews.dto.filterdto.PageSortFilterDto;
import com.example.Nnnews.dto.entitydto.UserDto;
import com.example.Nnnews.services.serviceInterfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserBean userBean;
    @Autowired
    private UserService userService;

    @PostMapping("/all")
    public Page getAllWithPageSortFilterTest(@RequestBody PageSortFilterDto dto) throws NoSuchFieldException {
        return userBean.findAllWithPageFilter(dto);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable UUID id) {
        userBean.deleteById(id);
    }

    @PostMapping
    public UserDto saveUser(@RequestBody UserDto userDto) {
        return userBean.saveUser(userDto);
    }

    @GetMapping("/{id}")
    public UserDto getUserById(@PathVariable UUID id) {
        return userBean.findById(id);
    }

    @PutMapping("/{id}")
    public UserDto updateUser(@PathVariable UUID id, @RequestBody UserDto dto) {
        return userBean.updateEntity(id, dto);
    }

}
