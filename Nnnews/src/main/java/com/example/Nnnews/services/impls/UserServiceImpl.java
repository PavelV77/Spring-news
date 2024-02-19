package com.example.Nnnews.services.impls;

import com.example.Nnnews.dto.entitydto.UserDto;
import com.example.Nnnews.entities.User;
import com.example.Nnnews.repositories.UserRepository;
import com.example.Nnnews.services.serviceInterfaces.UserService;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserServiceImpl extends BaseServiceImpl<User, UserRepository> implements UserService {
    private UserRepository repository;

    public UserServiceImpl(UserRepository repository) {
        super(repository);
        this.repository = repository;
    }

    @Override
    public User createUser(String login) {
        User user = new User();
        user.setLogin(login);
        return repository.save(user);
    }

    @Override
    public User findByLogin(String login) {
        return repository.findByLogin(login);
    }


    public User updateEntity(UUID id, UserDto dto) {
        User oldUser = repository.findById(id).get();
        oldUser.setLogin(dto.getLogin());
        return repository.save(oldUser);
    }

}
