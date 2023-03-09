package com.example.securityprj04.controller;

import com.example.securityprj04.model.Post;
import com.example.securityprj04.repository.PostRepo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/posts")
public class PostController {

    private final PostRepo postRepo;

    public PostController(PostRepo postRepo) {
        this.postRepo = postRepo;
    }

    @GetMapping
    public Iterable<Post> findAll(){
       return postRepo.findAll();
    }
/** с раскомиченным вот этим методом не app запускается, видимо потому что один и тот же http метод на один и тот же адрес */
//    @GetMapping("/{id}")
//    public Post findById(@PathVariable("id") Long id){
//        Optional<Post> post = postRepo.findById(id);
//        if(post.isEmpty()){
//            throw new IllegalArgumentException();
//        }
//        return post.get();
//    }
    /** а можно все то же самое, но вот так. Ну проверок, наверное не будет, правда)))*/
    @GetMapping("/{id}")
    public Post findByIdAnother(@PathVariable("id") Post post){
        return post;
    }
}
