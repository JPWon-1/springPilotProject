package com.start.pilotproject.web;

import com.start.pilotproject.domain.posts.PostsRepository;
import com.start.pilotproject.service.posts.PostsService;
import com.start.pilotproject.web.dto.PostsSaveRequestDto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class PostsApiController {
    private final PostsService postsService;

    @Autowired
    PostsRepository postsRepo;

    @PostMapping("/api/v1/posts")
    public Long save(@RequestBody PostsSaveRequestDto requestDto){
        return postsService.save(requestDto);
    }
}