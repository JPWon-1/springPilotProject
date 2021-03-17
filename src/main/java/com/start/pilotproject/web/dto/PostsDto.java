package com.start.pilotproject.web.dto;

import com.start.pilotproject.domain.posts.Posts;

import lombok.Builder;
import lombok.Getter;

public class PostsDto {

    @Getter
    public static class Response{
        private Long id;
        private String title;
        private String content;
        private String author;

        public Response(Posts entity){
            this.id = entity.getId();
            this.title = entity.getTitle();
            this.content = entity.getContent();
            this.author = entity.getAuthor();
        }    
    }

    @Getter
    public static class Request{
        private String title;
        private String content;
        @Builder
        public Request(String title,String content){
            this.title = title;
            this.content = content;
        }
    }

    
    
}
