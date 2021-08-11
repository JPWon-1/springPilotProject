package com.start.pilotproject.controller.comment;

import com.start.pilotproject.controller.comment.dto.CommentRequestDto;
import com.start.pilotproject.service.comment.CommentService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RequestMapping("/comment")
@RestController
public class CommentApiController {
    private final CommentService commentService;

    @PostMapping("/api")//작성
    public ResponseEntity write(CommentRequestDto dto){
        commentService.save(dto.toEntity());
        return new ResponseEntity(null, null);
    }

    @PatchMapping("/api/{id}")//수정
    public ResponseEntity update(CommentRequestDto dto){
        commentService.update(dto.toEntity());
        return new ResponseEntity(null, null);
    }

    @DeleteMapping("/api/{id}")//삭제
    public void delete(@PathVariable Long id){
        commentService.deleteById(id);
    }

}