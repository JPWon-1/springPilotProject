package com.start.pilotproject.repository.post;


import java.util.List;

import com.start.pilotproject.domain.posts.Post;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends JpaRepository<Post,Long>{

    @Query("SELECT p FROM Post p ORDER by p.id DESC")
    List<Post> findAllDesc();

    @Query("SELECT p FROM Post p where p.title = ?1")
    Post findByTitle(String title);
    
}