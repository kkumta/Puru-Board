package com.puru.puruboard.service;

import com.puru.puruboard.dto.CreateReplyDto;
import com.puru.puruboard.dto.ReplyResponseDto;
import java.util.List;

public interface ReplyService {
    
    // Create
    Long createReply(Long postId, CreateReplyDto createReplyDto);
    
    // Read
    List<ReplyResponseDto> findAllByPostId(Long postId);
    
    // Update
    
    // Delete
}
