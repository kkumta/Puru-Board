package com.puru.puruboard.controller;

import com.puru.puruboard.domain.Post;
import com.puru.puruboard.domain.UserRepository;
import com.puru.puruboard.dto.CreatePostDto;
import com.puru.puruboard.dto.PostListResponseDto;
import com.puru.puruboard.dto.PostResponseDto;
import com.puru.puruboard.dto.UpdatePostDto;
import com.puru.puruboard.service.PostService;
import com.puru.puruboard.service.UserService;
import java.util.List;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@RequiredArgsConstructor
@Controller
@RequestMapping
public class PostController {
    
    private final PostService postService;
    private final UserRepository userRepository;
    
    // 게시글 작성 폼
    @GetMapping("/posts/create")
    public String createPostForm(@ModelAttribute("post") CreatePostDto createPostDto) {
        return "post/create-post";
    }
    
    // 게시글 작성
    @PostMapping("/posts/create")
    public String createPost(@Valid @ModelAttribute CreatePostDto createPostDto,
                             RedirectAttributes redirectAttributes) {
        
        Long postId = postService.createPost(createPostDto);
        redirectAttributes.addAttribute("postId", postId);
        
        return "redirect:/board/{postId}";
    }
    
    // 게시글 수정 폼
    @GetMapping("/posts/{postId}/update")
    public String updatePostForm(@PathVariable Long postId, Model model) {
        
        PostResponseDto post = postService.findPost(postId);
        model.addAttribute("post", post);
        
        return "post/update-post";
    }

    // 게시글 수정
    @PostMapping("/posts/{postId}/update")
    public String updatePost(@PathVariable Long postId, @ModelAttribute UpdatePostDto updatePostDto) {
    
        postService.updatePost(postId, updatePostDto);
        
        return "redirect:/board/{postId}";
    }
    
    // 게시글 삭제
    @GetMapping("/posts/{postId}/delete")
    public String deletePost(@PathVariable Long postId) {
        
        postService.deletePost(postId);
        
        return "redirect:/board";
    }
    
    // 게시글 단건 조회
    @GetMapping("/board/{postId}")
    public String readPost(@PathVariable Long postId, Model model) {
        PostResponseDto post = postService.findPost(postId);
        model.addAttribute("post", post);
        model.addAttribute("user", false);
    
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        // authentication 검증
        if (authentication != null && !AnonymousAuthenticationToken.class.isAssignableFrom(authentication.getClass())) {
            String curUserNickname = userRepository.findByEmail(SecurityContextHolder.getContext().getAuthentication().getName()).get().getNickname();
            if (post.getAuthor().equals(curUserNickname)) {
                model.addAttribute("user", true);
            }
        }
        
        return "post/post-info";
    }
    
    // 게시글 목록 조회(자유 게시판) -> 추후 페이징으로 변경 예정
    @GetMapping("/board")
    public String readAllPosts(Model model) {
        
        List<PostListResponseDto> posts = postService.findAllDesc();
        
        model.addAttribute("posts", posts);
        model.addAttribute("boardName", "자유 게시판");
        
        return "post/post-list";
    }
}