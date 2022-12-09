package com.puru.puruboard.dto;

import com.puru.puruboard.domain.Post;
import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PostListResponseDto {
    
    private Long id;
    private String title;
    private String author;
    private LocalDateTime createdDate;
    
    @Builder
    public PostListResponseDto(Long id, String title, String author, LocalDateTime createdDate) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.createdDate = createdDate;
    }
    
    public PostListResponseDto(Post post) {
        this.id = post.getId();
        this.title = post.getTitle();
        this.author = post.getAuthor();
        this.createdDate = post.getCreatedDate();
    }
}