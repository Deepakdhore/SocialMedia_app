package com.socialMedia.socialMediaApp.services.postServices;

import com.socialMedia.socialMediaApp.entities.Post;

import java.util.List;
import java.util.Optional;

public interface PostService {
    Post createPost(Post post);
    List<Post> getPostsByUsername(String username);
    Optional<Post> getPostById(Long id);

}
