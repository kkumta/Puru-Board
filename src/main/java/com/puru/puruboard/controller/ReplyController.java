package com.puru.puruboard.controller;

import com.puru.puruboard.dto.CreateReplyDto;
import com.puru.puruboard.service.ReplyService;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@RequiredArgsConstructor
@Controller
@RequestMapping
public class ReplyController {
    
    private final ReplyService replyService;
    
    // 댓글 작성
    @PostMapping("/posts/{postId}/reply/create")
    public String createReply(@PathVariable Long postId,
                              @Valid @ModelAttribute CreateReplyDto createReplyDto,
                              RedirectAttributes redirectAttributes) {
        
        replyService.createReply(postId, createReplyDto);
        redirectAttributes.addAttribute("postId", postId);
        
        return "redirect:/board/{postId}";
    }
    
    // 댓글 수정
    
    // 댓글 삭제
    // 게시글 삭제
    @GetMapping("/reply/{postId}/{replyId}/delete")
    public String deleteReply(@PathVariable Long postId, @PathVariable Long replyId) {
    
        System.out.println("postId = " + postId);
        System.out.println("replyId = " + replyId);
        
        replyService.deleteReply(replyId);
    
        return "redirect:/board/{postId}";
    }
}