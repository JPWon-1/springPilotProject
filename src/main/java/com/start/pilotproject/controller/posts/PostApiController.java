package com.start.pilotproject.controller.posts;

import com.start.pilotproject.controller.posts.dto.PostsSaveRequestDto;
import com.start.pilotproject.controller.posts.dto.PostsDto.PostsUpdateRequestDto;
import com.start.pilotproject.service.posts.PostService;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RequestMapping("/post")
@RestController
public class PostApiController {
    private final PostService postsService;

    @PostMapping("/api/v1/posts")//작성
    public Long save(@RequestBody PostsSaveRequestDto requestDto){
        return postsService.save(requestDto);
    }

    @PutMapping("/api/v1/posts/{id}")//수정
    public Long update(@PathVariable Long id , @RequestBody PostsUpdateRequestDto requestDto){
        return postsService.update(id,requestDto);
    }

    // @GetMapping("/api/v1/posts/{id}")//조회
    // public PostsResponse findById(@PathVariable Long id){
    //     return postsService.getOne(id);
    // }

    @DeleteMapping("/api/v1/posts/{id}")//삭제
    public Long delete(@PathVariable Long id){
        postsService.delete(id);
        return id;
    }

}