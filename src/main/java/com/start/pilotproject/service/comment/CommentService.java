package com.start.pilotproject.service.comment;

import com.start.pilotproject.domain.comment.Comment;
import com.start.pilotproject.repository.comment.CommentRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CommentService {

    @Autowired
    CommentRepository commentRepository;

    @Transactional
    public void save(Comment comment) {
        commentRepository.save(comment);
    }
    
    @Transactional
    public void update(Comment comment) {
        commentRepository.save(comment);
    }
    
    @Transactional
    public void deleteById(Long id) {
        commentRepository.deleteById(id);
    }
    
}
