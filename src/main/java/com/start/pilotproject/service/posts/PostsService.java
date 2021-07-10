package com.start.pilotproject.service.posts;

import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.start.pilotproject.controller.posts.dto.PostsSaveRequestDto;
import com.start.pilotproject.controller.posts.dto.PostsDto.PostsResponse;
import com.start.pilotproject.controller.posts.dto.PostsDto.PostsUpdateRequestDto;
import com.start.pilotproject.domain.posts.Post;
import com.start.pilotproject.domain.posts.QPost;
import com.start.pilotproject.repository.post.PostsRepository;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class PostsService {
    private final PostsRepository postsRepository;

    @PersistenceContext // 영속성 객체를 자동으로 삽입해줌
    private EntityManager em; 

    @Transactional(readOnly = true)
    public List<PostsResponse> findAllDesc(){
        return postsRepository.findAllDesc().stream()
                .map(PostsResponse::new)
                .collect(Collectors.toList());
    }
    
    @Transactional(readOnly = true)
    public PostsResponse getOne(Long id){
        JPAQueryFactory queryFactory = new JPAQueryFactory(em);
        QPost post = QPost.post; 
        return queryFactory.from(post)
            .select(Projections.fields(PostsResponse.class,
                post.id,
                post.author,
                post.title,
                post.content,
                post.createdDate,
                post.modifiedDate
            )).where(post.id.eq(id))
            .fetchOne();
    }

    @Transactional
    public Long save(PostsSaveRequestDto requestDto){
        return postsRepository.save(requestDto.toEntity()).getId();
    }
    
    @Transactional
    public void delete(Long id){
        Post posts = postsRepository.findById(id)
                .orElseThrow(()->new IllegalArgumentException("해당 게시글이 없습니다. id="+id));
        postsRepository.delete(posts);
    }


    @Transactional
    public Long update(Long id, PostsUpdateRequestDto requestDto){
        Post posts = postsRepository.findById(id).orElseThrow(
            ()->new IllegalArgumentException("해당 게시글이 없습니다"));
        posts.update(requestDto.getTitle(), requestDto.getContent());
        return id;
    }

}