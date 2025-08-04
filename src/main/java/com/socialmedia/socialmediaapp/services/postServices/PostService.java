package com.socialmedia.socialmediaapp.services.postServices;

import com.socialmedia.socialmediaapp.dto.FullPostDto;
import com.socialmedia.socialmediaapp.dto.PostDto;
import com.socialmedia.socialmediaapp.entities.Post;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface PostService {
    Post createPost(Map<String,String> post,String username);
    List<FullPostDto> getPostsByUsername(String username);
    Optional<Post> getPostById(Long id);
    List<FullPostDto> getFeedByCategory(String category);


}
