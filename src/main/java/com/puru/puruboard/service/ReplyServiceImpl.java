package com.puru.puruboard.service;

import com.puru.puruboard.domain.Reply;
import com.puru.puruboard.domain.ReplyRepository;
import com.puru.puruboard.dto.ReplyResponseDto;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class ReplyServiceImpl implements ReplyService {
    
    private final ReplyRepository replyRepository;
    
    @Override
    public List<ReplyResponseDto> findAllByPostId(Long postId) {
        List<Reply> replyList = replyRepository.findAllByPostId(postId);
        
        return replyList.stream().filter(r -> !r.getIsDeleted())
            .map(ReplyResponseDto::new)
            .collect(Collectors.toList());
    }
}