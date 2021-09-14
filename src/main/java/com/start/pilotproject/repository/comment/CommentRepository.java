package com.start.pilotproject.repository.comment;

import java.util.List;

import javax.transaction.Transactional;

import com.start.pilotproject.domain.comment.Comments;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CommentRepository extends JpaRepository<Comments, Long> {

    List<Comments> findByHistoryId(Long historyId);

    @Transactional
    @Modifying
    @Query("delete from Comments where historyId=:historyId and id=:id")
    void deleteByHistoryIdAndId(@Param("historyId") Long historyId, @Param("id") Long id);

}
