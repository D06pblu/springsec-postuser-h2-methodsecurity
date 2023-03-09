package com.example.securityprj04.repository;

import com.example.securityprj04.model.Post;
import org.springframework.data.repository.CrudRepository;

public interface PostRepo extends CrudRepository<Post, Long> {
}
