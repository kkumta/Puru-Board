package com.puru.puruboard.dto;

import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Getter;

@Getter
public class PostResponseDto {
    
    private Long id;
    private String title;
    private String author;
    private String content;
    private LocalDateTime createdDate;
    private LocalDateTime lastEditDate;
    
    @Builder
    public PostResponseDto(Long id, String title, String author, String content,
                           LocalDateTime createdDate, LocalDateTime lastEditDate) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.content = content;
        this.createdDate = createdDate;
        this.lastEditDate = lastEditDate;
    }
}
