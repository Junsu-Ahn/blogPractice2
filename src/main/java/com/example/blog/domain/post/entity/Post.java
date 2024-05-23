package com.example.blog.domain.post.entity;

import com.example.blog.global.jpa.BaseEntity;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

@Entity
@Getter
@SuperBuilder
public class Post extends BaseEntity {

    private String title;
    private String content;
}