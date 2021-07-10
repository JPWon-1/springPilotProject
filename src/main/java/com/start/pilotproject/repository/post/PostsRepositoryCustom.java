package com.start.pilotproject.repository.post;

import com.start.pilotproject.domain.posts.Post;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface PostsRepositoryCustom extends JpaRepository<Post,Long>,QuerydslPredicateExecutor<Post>{
    
}