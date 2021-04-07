package com.start.pilotproject.domain.dto;

import java.time.LocalDateTime;

import com.start.pilotproject.domain.posts.Posts;


import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class PostsDto {

    @Getter
    public static class PostsResponse{
        private Long id;
        private String title;
        private String author;
        private String content;
        private LocalDateTime modifiedDate;
        private LocalDateTime createdDate;

        public PostsResponse(Long id, String title, String author, String content,
         LocalDateTime createdDate, LocalDateTime modifiedDate) {
            this.id = id;
            this.title = title;
            this.content = content;
            this.author = author;
        }

        public PostsResponse(Posts entity){
            this.id = entity.getId();
            this.title = entity.getTitle();
            this.author = entity.getAuthor();
            this.content = entity.getContent();
            this.modifiedDate = entity.getModifiedDate();
            this.createdDate = entity.getCreatedDate();
        }
    }

    @NoArgsConstructor
    @Getter
    public static class PostsUpdateRequestDto{
        private String title;
        private String content;

        @Builder
        public PostsUpdateRequestDto(String title,String content){
            this.title = title;
            this.content = content;
        }
    }

    
    
}
