package com.puru.puruboard.service;

import com.puru.puruboard.dto.CreatePostDto;
import com.puru.puruboard.dto.PostListResponseDto;
import com.puru.puruboard.dto.PostResponseDto;
import com.puru.puruboard.dto.UpdatePostDto;
import java.util.List;

public interface PostService {
    
    Long createPost(CreatePostDto createPostDto);
    
    Long updatePost(Long postId, UpdatePostDto updatePostDto);
    
    void deletePost(Long id);
    
    PostResponseDto findPost(Long id);
    
    List<PostListResponseDto> findAllDesc();
}
