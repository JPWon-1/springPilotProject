package com.start.pilotproject.controller.posts;

import java.util.List;

import com.start.pilotproject.domain.posts.Posts;
import com.start.pilotproject.domain.posts.PostsRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/posts")
@Controller
public class PostController {
    @Autowired
    private PostsRepository postRepo;

    @GetMapping
    public String getPosts(Model model){
        List<Posts> list = postRepo.findAll();
        model.addAttribute("list", list);
        return "posts/posts";
    }

    @GetMapping("/{id}")
    public String getPost(Model model, @PathVariable Long id){
        Posts list = postRepo.getOne(id);
        model.addAttribute("list", list);
        return "posts/post";
    }

    @GetMapping("/write")
    public String writePost(){
        return "posts/write";
    }
}
