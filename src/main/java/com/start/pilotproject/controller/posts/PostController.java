package com.start.pilotproject.controller.posts;

import com.start.pilotproject.service.posts.PostsService;

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
    
    private final PostsService postsService;
    
    @GetMapping
    public String getPosts(Model model){
        model.addAttribute("list", postsService.findAllDesc());
        return "posts/posts";
    }

    @GetMapping("/{id}")
    public String getPost(Model model, @PathVariable Long id){
        model.addAttribute("list", postsService.getOne(id));
        return "posts/post";
    }

    @GetMapping("/write")
    public String writePost(){
        return "posts/write";
    }
}
