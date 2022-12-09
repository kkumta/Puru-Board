package com.puru.puruboard.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Entity
public class Post extends BaseTimeEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private String title;
    
    @Column(nullable = false)
    private String author;
    
    @Column(nullable = false)
    private String content;
    
    @Column
    private Boolean isDeleted;
    
    @Builder
    public Post(String title, String author, String content) {
        this.title = title;
        this.author = author;
        this.content = content;
        this.isDeleted = false;
    }
    
    public void update(String title, String author, String content) {
        this.title = title;
        this.author = author;
        this.content = content;
    }
    
    public void delete() {
        this.isDeleted = true;
    }
}
