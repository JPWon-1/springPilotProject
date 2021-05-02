package com.start.pilotproject.repository.post;

import com.start.pilotproject.domain.posts.Posts;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface PostsRepositoryCustom extends JpaRepository<Posts,Long>,QuerydslPredicateExecutor<Posts>{
    
}