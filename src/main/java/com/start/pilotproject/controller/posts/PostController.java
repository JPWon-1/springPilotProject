package com.start.pilotproject.controller.posts;

import com.start.pilotproject.service.posts.PostService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.RequiredArgsConstructor;

@RequestMapping("/posts")
@RequiredArgsConstructor
@Controller
public class PostController {
    
    private final PostService postService;
    
    //포스트 리스트 조회
    @GetMapping
    public String getPosts(Model model){
        model.addAttribute("list", postService.findAllDesc());
        return "posts/postIndex";
    }

    //포스트 조회
    @GetMapping("/{id}")
    public String getPost(Model model, @PathVariable Long id){
        model.addAttribute("list", postService.getOne(id));
        return "posts/post";
    }

    //글쓰기 페이지
    @GetMapping("/write")
    public String writePost(){
        return "posts/write";
    }

    //글쓰기 페이지(수정)
    @GetMapping("/write/{id}")
    public String patchPost(Model model, @PathVariable Long id){
        model.addAttribute("list", postService.getOne(id));
        return "posts/write";
    }
}
