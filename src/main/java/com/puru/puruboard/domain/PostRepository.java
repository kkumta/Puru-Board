package com.puru.puruboard.domain;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
    
//    List<Post> findAllDesc();
    Optional<Post> findById(Long id);
}
