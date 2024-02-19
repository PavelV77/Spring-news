package com.example.Nnnews.repositories;

import com.example.Nnnews.entities.User;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;
@Repository
public interface UserRepository extends CrudRepository<User, UUID>, BaseRepository<User>{
    User findByLogin(String login);
    void deleteByLogin(String login);
}
