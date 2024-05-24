package com.example.blog.domain.post.entity;

import com.example.blog.domain.member.entity.Member;
import com.example.blog.global.jpa.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@Entity
@Getter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@ToString

public class Post extends BaseEntity {

    private long id;
    @Column(length = 200)
    private String title;

    @Column(columnDefinition = "TEXT")
    private String content;


    private LocalDateTime createDate;

    @ManyToOne
    private Member author;
}