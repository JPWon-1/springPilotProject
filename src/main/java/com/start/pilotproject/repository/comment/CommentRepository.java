package com.start.pilotproject.repository.comment;


import java.util.List;

import com.start.pilotproject.domain.comment.Comment;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment,Long>{

    List<Comment> findByHistoryId(Long historyId);

}
