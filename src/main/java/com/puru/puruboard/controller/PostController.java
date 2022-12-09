package com.puru.puruboard.controller;

import com.puru.puruboard.domain.Post;
import com.puru.puruboard.dto.CreatePostDto;
import com.puru.puruboard.dto.PostListResponseDto;
import com.puru.puruboard.dto.PostResponseDto;
import com.puru.puruboard.service.PostService;
import java.util.List;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@RequiredArgsConstructor
@Controller
@RequestMapping
public class PostController {
    
    private final PostService postService;
    
    // 게시글 작성 폼
    @GetMapping("/posts/create")
    public String createPostForm(@ModelAttribute("post") CreatePostDto createPostDto) {
        return "post/create-post";
    }
    
    // 게시글 작성
    @PostMapping("/posts/create")
    public String createPost(@Valid @ModelAttribute CreatePostDto createPostDto,
                             RedirectAttributes redirectAttributes) {
        
        System.out.println("createPostDto = " + createPostDto.getTitle());
        System.out.println("createPostDto = " + createPostDto.getContent());
        
        Long postId = postService.createPost(createPostDto);
        redirectAttributes.addAttribute("postId", postId);
        
        return "redirect:/board/{postId}";
    }
    
    // 게시글 수정 폼
    
    // 게시글 수정
    
    // 게시글 삭제
    
    // 게시글 단건 조회
    @GetMapping("/board/{postId}")
    public String readPost(@PathVariable Long postId, Model model) {
        PostResponseDto post = postService.findPost(postId);
        model.addAttribute("post", post);
        
        return "post/post-info";
    }
    
    // 게시글 목록 조회 -> 추후 페이징으로 변경 예정
    @GetMapping("/board")
    public String readAllPosts(Model model) {
        List<PostListResponseDto> posts = postService.findAllDesc();
        model.addAttribute("posts", posts);
        
        return "post/post-list";
    }
}