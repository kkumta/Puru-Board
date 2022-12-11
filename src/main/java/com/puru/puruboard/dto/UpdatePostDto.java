package com.puru.puruboard.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
public class UpdatePostDto {
    
    private String title;
    private String content;
    
    @Builder
    public UpdatePostDto(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
