package com.start.pilotproject.domain.posts;

import com.querydsl.jpa.impl.JPAQueryFactory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostsRepository extends JpaRepository<Posts,Long>{
    
}