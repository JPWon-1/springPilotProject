package com.start.pilotproject.domain.comment;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

import com.start.pilotproject.util.BaseTimeEntity;

import org.springframework.data.annotation.CreatedBy;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Entity
public class Comment extends BaseTimeEntity {
    @Id
    @SequenceGenerator(name = "comment_seq", sequenceName = "comment_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "comment_seq")
    private Long id;

    @Column(name = "history_id")
    private Long historyId;

    @CreatedBy
    @Column(name = "member_id")
    private Long memberId;

    @Column
    private String content;

    @Builder
    public Comment(Long historyId, Long memberId, String content) {
        this.historyId = historyId;
        this.content = content;
    }
}
