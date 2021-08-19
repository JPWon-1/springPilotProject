package com.start.pilotproject.controller.comment;

import com.start.pilotproject.controller.comment.dto.CommentRequestDto;
import com.start.pilotproject.service.comment.CommentService;
import com.start.pilotproject.util.ResponseMessage;
import com.start.pilotproject.util.StatusEnum;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RequestMapping("/comment/api")
@RestController
public class CommentApiController {
    private final CommentService commentService;

    @PostMapping("/v1/comment") // 작성
    public String write(@RequestBody CommentRequestDto dto) {
        commentService.save(dto.toEntity());
        ResponseMessage message = new ResponseMessage();
        HttpHeaders headers = new HttpHeaders();
        headers.add("comment_id", String.valueOf(dto.getId()));
        message.setStatus(StatusEnum.OK);
        message.setMessage("성공");
        return "detail :: #comment_list";
    }

    @PatchMapping("/v1/comment/{id}") // 수정
    public ResponseEntity<ResponseMessage> update(CommentRequestDto dto) {
        commentService.update(dto.toEntity());
        ResponseMessage message = new ResponseMessage();
        HttpHeaders headers = new HttpHeaders();
        headers.add("comment_id", String.valueOf(dto.getId()));
        message.setStatus(StatusEnum.OK);
        message.setMessage("성공");
        return new ResponseEntity<>(message, headers, HttpStatus.OK);
    }

    @DeleteMapping("/v1/comment/{id}") // 삭제
    public void delete(@PathVariable Long id) {
        commentService.deleteById(id);
    }

}