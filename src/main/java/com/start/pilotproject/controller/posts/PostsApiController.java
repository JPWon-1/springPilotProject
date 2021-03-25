package com.start.pilotproject.controller.posts;

import com.start.pilotproject.domain.posts.PostsRepository;
import com.start.pilotproject.service.posts.PostsService;
import com.start.pilotproject.domain.dto.PostsDto;
import com.start.pilotproject.domain.dto.PostsDto.PostsUpdateRequestDto;
import com.start.pilotproject.domain.dto.PostsSaveRequestDto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class PostsApiController {
    private final PostsService postsService;

    @PostMapping("/api/v1/posts")//작성
    public Long save(@RequestBody PostsSaveRequestDto requestDto){
        return postsService.save(requestDto);
    }

    @PutMapping("/api/v1/posts/{id}")//수정
    public Long update(@PathVariable Long id , @RequestBody PostsUpdateRequestDto requestDto){
        return postsService.update(id,requestDto);
    }

    @DeleteMapping("/api/v1/posts/{id}")//삭제
    public Long delete(@PathVariable Long id){
        postsService.delete(id);
        return id;
    }

}