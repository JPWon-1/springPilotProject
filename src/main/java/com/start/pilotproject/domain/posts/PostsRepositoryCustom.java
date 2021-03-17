package com.start.pilotproject.domain.posts;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface PostsRepositoryCustom extends JpaRepository<Posts,Long>,QuerydslPredicateExecutor<Posts>{
    
}