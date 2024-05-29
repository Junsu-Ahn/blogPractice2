package com.example.blog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class BlogApplication {
    // C -> work -> thumbnail -> post 파일만들기
    public static void main(String[] args) {
        SpringApplication.run(BlogApplication.class, args);
    }

}
