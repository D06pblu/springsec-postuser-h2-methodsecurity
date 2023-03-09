package com.example.securityprj04.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Post {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String author;
    private String title;
    private String tag;
    private String content;
    private LocalDateTime postedOn;
    private LocalDateTime editedOn;

    public Post(String author, String title, String tag, String content) {
        this.author = author;
        this.title = title;
        this.tag = tag;
        this.content = content;
        this.postedOn = LocalDateTime.now();
    }
}


