package com.start.pilotproject.repository.post;


import java.util.List;

import com.start.pilotproject.domain.posts.Posts;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PostsRepository extends JpaRepository<Posts,Long>{

    @Query("SELECT p FROM Posts p ORDER by p.id DESC")
    List<Posts> findAllDesc();

    @Query("SELECT p FROM Posts p where p.title = ?1")
    Posts findByTitle(String title);
    
}