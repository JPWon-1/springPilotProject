package com.start.pilotproject.controller.comment.dto;

import com.start.pilotproject.domain.comment.Comments;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CommentRequestDto {
    private Long id;
    private Long historyId;
    private Long memberId;
    private String content;

    public Comments toEntity() {
        return Comments.builder()
            .historyId(historyId)
            .content(content)
            .build();
    }
}
