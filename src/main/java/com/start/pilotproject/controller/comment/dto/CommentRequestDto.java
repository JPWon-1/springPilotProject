package com.start.pilotproject.controller.comment.dto;

import com.start.pilotproject.domain.comment.Comment;

import lombok.Getter;

@Getter
public class CommentRequestDto {
    private Long historyId;
    private Long memberId;
    private String content;

    public Comment toEntity() {
        return Comment.builder()
            .historyId(historyId)
            .memberId(memberId)
            .content(content)
            .build();
    }
}
