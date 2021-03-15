package com.start.pilotproject.domain.posts;

import java.util.List;

public interface PostsRepositoryCustom {
    List<Posts> search(Long id);
}
