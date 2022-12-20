package com.puru.puruboard.dto;

import com.puru.puruboard.domain.Reply;
import java.time.LocalDateTime;
import lombok.Getter;

@Getter
public class ReplyResponseDto {
    
    private Long id;
    private String author;
    private String content;
    private LocalDateTime createdDate;
    private LocalDateTime lastEditDate;
    
    private Boolean isMyReply = false;
    
    public ReplyResponseDto(Reply reply) {
        this.id = reply.getId();
        this.author = reply.getAuthor();
        this.content = reply.getContent();
        this.createdDate = reply.getCreatedDate();
        this.lastEditDate = reply.getLastEditDate();
    }
    
    public void changeIsMyReply() {
        isMyReply = true;
    }
}