package com.puru.puruboard.domain;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReplyRepository extends JpaRepository<Reply, Long> {
    
    List<Reply> findAllByPostId(Long postId);
}
