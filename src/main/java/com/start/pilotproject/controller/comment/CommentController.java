package com.start.pilotproject.controller.comment;

import java.util.List;

import com.start.pilotproject.controller.comment.dto.CommentRequestDto;
import com.start.pilotproject.domain.comment.Comment;
import com.start.pilotproject.repository.comment.CommentRepository;
import com.start.pilotproject.service.comment.CommentService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RequestMapping("/comment")
@Controller
public class CommentController {

    private final CommentService commentService;
    private final CommentRepository commentRepository;

    @PostMapping // 작성
    public String write(@RequestBody CommentRequestDto dto, Model model) {
        commentService.save(dto.toEntity());
        List<Comment> comments = commentRepository.findByHistoryId(dto.getHistoryId());
        model.addAttribute("comments",comments);
        return "detail :: #comment_list";
    }
   
}
