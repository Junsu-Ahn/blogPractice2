package com.example.blog.domain.post.service;

import com.example.blog.domain.post.entity.Post;
import com.example.blog.domain.post.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.time.DateTimeException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;

    @Value("${custom.fileDirPath}")
    private String fileDirPath;

    public List<Post> getList() {
        return postRepository.findAll();
    }

    public void create(String title, String content, MultipartFile thumbnail) {
        String thumbnailRelPath = "post/" + UUID.randomUUID().toString() + ".jpg";
        File thumbnailFile = new File(fileDirPath + "/" + thumbnailRelPath);

        try {
            thumbnail.transferTo(thumbnailFile);
        } catch ( IOException e ) {
            throw new RuntimeException(e);
        }

        Post post = Post.builder()
                .title(title)
                .content(content)
                .thumbnailImg(thumbnailRelPath)
                .build();
        postRepository.save(post);
    }

    public Post getPost(Long id) {
        Optional<Post> op = postRepository.findById(id);
        if(op.isPresent())
            return op.get();
        else
            throw new DateTimeException("post not found");
    }
}