package com.start.pilotproject.service.posts;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.start.pilotproject.domain.posts.Posts;
import com.start.pilotproject.domain.posts.PostsRepository;
import com.start.pilotproject.domain.posts.QPosts;
import com.start.pilotproject.domain.dto.PostsDto;
import com.start.pilotproject.domain.dto.PostsDto.PostsUpdateRequestDto;
import com.start.pilotproject.domain.dto.PostsSaveRequestDto;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class PostsService {
    private final PostsRepository postsRepository;

     @PersistenceContext // 영속성 객체를 자동으로 삽입해줌
     private EntityManager em; 

    @Transactional
    public Long save(PostsSaveRequestDto requestDto){
        return postsRepository.save(requestDto.toEntity()).getId();
    }
    @Transactional
    public void delete(Long id){
        Posts posts = postsRepository.findById(id)
                .orElseThrow(()->new IllegalArgumentException("해당 게시글이 없습니다. id="+id));
        postsRepository.delete(posts);
    }

    @Transactional
    public List<Posts> search(){
        JPAQueryFactory queryFactory = new JPAQueryFactory(em);
        QPosts posts = QPosts.posts; 
        return queryFactory.selectFrom(posts).fetch();
    }

    @Transactional
    public Long update(Long id, PostsUpdateRequestDto requestDto){
        Posts posts = postsRepository.findById(id).orElseThrow(
            ()->new IllegalArgumentException("해당 게시글이 없습니다"));
        posts.update(requestDto.getTitle(), requestDto.getContent());
        return id;
    }

    public PostsDto.Response findById(Long id){
        Posts entity = postsRepository.findById(id).orElseThrow(
            ()->new IllegalArgumentException("해당 게시글이 없습니다"));
        return new PostsDto.Response(entity);
    }
}