package com.example.securityprj04.repository;

import com.example.securityprj04.model.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepo extends CrudRepository<User, Long> {

    Optional<User> findByUsername(String username);
}
