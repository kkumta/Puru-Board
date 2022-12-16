package com.puru.puruboard.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Entity
public class Reply extends BaseTimeEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "reply_id")
    private Long id;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id")
    private Post post;
    
    @Column(nullable = false)
    private String author;
    
    @Column(nullable = false)
    private String content;
    
    @Column
    private Boolean isDeleted;
    
    @Builder
    public Reply(Post post, String author, String content) {
        this.post = post;
        this.author = author;
        this.content = content;
        this.isDeleted = false;
    }
    
    public void update(String author, String content) {
        this.author = author;
        this.content = content;
    }
    
    public void delete() {
        this.isDeleted = true;
    }
}