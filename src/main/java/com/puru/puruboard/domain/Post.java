package com.puru.puruboard.domain;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
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

    @OneToMany(mappedBy = "post")
    private List<Reply> replyList = new ArrayList<>();
    
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
