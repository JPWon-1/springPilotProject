package com.start.pilotproject.controller.posts;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/posts")
@Controller
public class PostController {
    
    @GetMapping
    public String getPost(){
        return "posts/posts";
    }
    @GetMapping("/write")
    public String writePost(){
        return "posts/write";
    }
}
