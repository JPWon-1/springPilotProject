package com.start.pilotproject.domain.posts;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PostsRepository extends JpaRepository<Posts,Long>{
    @Query("Select p FROM Posts p ORDER BY p.id DESC")
    List<Posts> findAllDesc();
    
}