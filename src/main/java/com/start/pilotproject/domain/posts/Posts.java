package com.start.pilotproject.domain.posts;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

import org.springframework.stereotype.Component;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Component
@Entity
public class Posts {
    @Id
    @SequenceGenerator(name="post_seq", sequenceName="post_seq", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="post_seq")
    private Long id;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String title;

    @Column
    private String content;
    
    @Column
    private String author;

    @Builder
    public Posts(String title, String content, String author) {
        this.title = title;
        this.content = content;
        this.author = author;
    }

    public void update(String title, String content) {
        this.title = title;
        this.content = content;
    }
}