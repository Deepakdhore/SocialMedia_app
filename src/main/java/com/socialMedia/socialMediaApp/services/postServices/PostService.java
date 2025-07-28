package com.socialMedia.socialMediaApp.services.postServices;

import com.socialMedia.socialMediaApp.dto.PostDto;
import com.socialMedia.socialMediaApp.entities.Post;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface PostService {
    Post createPost(Map<String,String> post,String username);
    List<PostDto> getPostsByUsername(String username);
    Optional<Post> getPostById(Long id);

}
