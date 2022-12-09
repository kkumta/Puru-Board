package com.puru.puruboard.service;

import com.puru.puruboard.domain.Post;
import com.puru.puruboard.domain.PostRepository;
import com.puru.puruboard.domain.UserRepository;
import com.puru.puruboard.dto.CreatePostDto;
import com.puru.puruboard.dto.PostListResponseDto;
import com.puru.puruboard.dto.PostResponseDto;
import com.puru.puruboard.dto.UpdatePostDto;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class PostServiceImpl implements PostService {
    
    private final PostRepository postRepository;
    private final UserRepository userRepository;
    
    @Override
    @Transactional
    public Long createPost(CreatePostDto createPostDto) {
        String nickname = userRepository.findByEmail(
            SecurityContextHolder.getContext().getAuthentication().getName()).get().getNickname();
        
        Post post = Post.builder()
            .title(createPostDto.getTitle())
            .author(nickname)
            .content(createPostDto.getContent())
            .build();
        
        return postRepository.save(post).getId();
    }
    
    @Override
    @Transactional
    public Long updatePost(UpdatePostDto updatePostDto) {
        return null;
    }
    
    @Override
    @Transactional
    public void deletePost(Long id) {
    
    }
    
    @Override
    public PostResponseDto findPost(Long id) {
        Optional<Post> post = postRepository.findById(id);
        if (post.isEmpty()) {
            return null;
        }
        
        return PostResponseDto.builder()
            .title(post.get().getTitle())
            .author(post.get().getAuthor())
            .content(post.get().getContent())
            .createdDate(post.get().getCreatedDate())
            .lastEditDate(post.get().getLastEditDate())
            .build();
    }
    
    @Override
    public List<PostListResponseDto> findAllDesc() {
        List<PostListResponseDto> posts = postRepository.findAll().stream()
            .filter(p -> !p.getIsDeleted())
            .map(PostListResponseDto::new)
            .collect(Collectors.toList());
        
        return posts;
    }
}
