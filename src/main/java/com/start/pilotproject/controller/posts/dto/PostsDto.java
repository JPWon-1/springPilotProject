package com.start.pilotproject.controller.posts.dto;

import java.time.LocalDateTime;

import com.start.pilotproject.domain.posts.Post;


import lombok.Getter;
import lombok.NoArgsConstructor;

public class PostsDto {

    @NoArgsConstructor
    @Getter
    public static class PostsResponse{
        private Long id;
        private String title;
        private String author;
        private String content;
        private LocalDateTime modifiedDate;
        private LocalDateTime createdDate;

        public PostsResponse(Post entity){
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

    }
    
}
