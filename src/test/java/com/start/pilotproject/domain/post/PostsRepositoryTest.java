package com.start.pilotproject.domain.post;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import com.querydsl.core.types.Predicate;
import com.start.pilotproject.controller.posts.dto.PostsDto.PostsResponse;
import com.start.pilotproject.domain.posts.Posts;
import com.start.pilotproject.domain.posts.QPosts;
import com.start.pilotproject.repository.post.PostsRepository;
import com.start.pilotproject.repository.post.PostsRepositoryCustom;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PostsRepositoryTest {
    @LocalServerPort
    private Integer port;

    @Autowired
    PostsRepository postsRepository;

    @Autowired
    PostsRepositoryCustom postsRepositoryCustom;

    @AfterEach
    public void teardown() throws Exception {
        postsRepository.deleteAll();
    }

    @Test
    public void 게시글저장_불러오기() {
        // given
        String title = "이것은 테스트입니다";
        String content = "content2";
        postsRepository.save(Posts.builder().title(title).content(content).author("jp@gmail.com").build());
        // when
        Posts post = postsRepository.findByTitle(title);

        // then
        assertThat(post.getTitle()).isEqualTo(title);
        assertThat(post.getContent()).isEqualTo(content);
    }

    @Test
    public void Posts_등록된다() throws Exception {
        // given
        String title = "title";
        String content = "content";
        List<Posts> all = postsRepository.findAll();
        assertThat(all.get(0).getTitle()).isEqualTo(title);
        assertThat(all.get(0).getContent()).isEqualTo(content);
    }

    @Test
    public void Posts_수정된다() {
        // given
        Posts savedPosts = postsRepository.save(
                Posts.builder().title("beforeUpdateTitle").content("beforeUpdateContent").author("author").build());
        String expectedTitle = "updatedTitle";// 업데이트할 제목
        String expectedContent = "updatedContent";// 업데이트할 내용
        // when
        // jpa dirty checking 이용한 업데이트
        Posts post = postsRepository.findByTitle(savedPosts.getTitle());
        post.update(expectedTitle, expectedContent);
        PostsResponse requestDto = new PostsResponse(post);
        // then
        assertThat(requestDto.getTitle()).isEqualTo("updatedTitle");
        assertThat(requestDto.getContent()).isEqualTo("updatedContent");
    }

    @Test
    public void BaseTimeEntity_등록() {
        // given
        LocalDateTime now = LocalDateTime.of(2019, 6, 4, 0, 0, 0);
        postsRepository.save(Posts.builder().title("title").content("content").author("author").build());
        // when
        List<Posts> postsList = postsRepository.findAll();
        // then
        Posts posts = postsList.get(0);

        System.out.println(">>>>>>>createDate=" + posts.getCreatedDate() + ",+modifiedDate=" + posts.getModifiedDate());
        assertThat(posts.getCreatedDate()).isAfter(now);
        assertThat(posts.getModifiedDate()).isAfter(now);

    }

    @Test
    public void 아이디로_찾는다() {
        QPosts posts = QPosts.posts;
        Predicate predicate = posts.author.eq("jj");
        Optional<Posts> post = postsRepositoryCustom.findOne(predicate);
        System.out.println("결과!?" + post);

    }
}
