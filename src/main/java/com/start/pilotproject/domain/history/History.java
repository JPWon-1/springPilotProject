package com.start.pilotproject.domain.history;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;

import com.start.pilotproject.domain.comment.Comment;
import com.start.pilotproject.util.BaseTimeEntity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Entity
public class History extends BaseTimeEntity{
    
    @Id
    @SequenceGenerator(name="history_seq", sequenceName="history_seq", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="history_seq")
    private Long id;

    private String historyDate;

    private String content;

    private String source;

    @OneToMany
    @JoinColumn(name = "history_id")
    private List<Comment> comments = new ArrayList<>();

    @Builder
    public History(Long id, String content, String source) {
        this.id = id;
        this.content = content;
        this.source = source;
        this.historyDate = LocalDateTime.now().toString().substring(0, 10);
    }
    
}
