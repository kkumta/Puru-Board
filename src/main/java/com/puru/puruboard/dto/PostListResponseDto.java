package com.puru.puruboard.dto;

import com.puru.puruboard.domain.Post;
import java.time.LocalDateTime;
import lombok.Getter;

@Getter
//@Setter
public class PostListResponseDto {
    
    private Long id;
    private String title;
    private String author;
    private LocalDateTime createdDate;
    
    public PostListResponseDto(Post post) {
        this.id = post.getId();
        this.title = post.getTitle();
        this.author = post.getAuthor();
        this.createdDate = post.getCreatedDate();
    }
}