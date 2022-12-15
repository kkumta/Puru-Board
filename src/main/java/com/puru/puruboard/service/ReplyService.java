package com.puru.puruboard.service;

import com.puru.puruboard.dto.ReplyResponseDto;
import java.util.List;

public interface ReplyService {
    
    // Create
    
    // Read
    List<ReplyResponseDto> findAllByPostId(Long postId);
    
    // Update
    
    // Delete
}
