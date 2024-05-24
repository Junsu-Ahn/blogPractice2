package com.example.blog;

import com.example.blog.domain.post.entity.Post;
import com.example.blog.domain.post.service.PostService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

@SpringBootTest
class BlogApplicationTests {

	@Autowired
	PostService postService;

	@Test
	void contextLoads() {
		for (int i = 0; i <=30 ; i++)
		{
			String title = String.format("제목 %d", i);
			String content = String.format("내용 %d",i);
			Post p = postService.create(title, content);
		}
	}

}
