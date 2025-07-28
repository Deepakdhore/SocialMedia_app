package com.socialMedia.socialMediaApp.services.postServices;

import com.socialMedia.socialMediaApp.dto.PostDto;
import com.socialMedia.socialMediaApp.entities.Post;
import com.socialMedia.socialMediaApp.repositories.PostRepository;
import com.socialMedia.socialMediaApp.repositories.UserRepository;
import com.socialMedia.socialMediaApp.services.notificationServices.CommentNotificationService;
import com.socialMedia.socialMediaApp.services.notificationServices.PostNotification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PostNotification postNotification;
    @Override
    public Post createPost(Map<String,String> post,String username) {

        Post newPost= new Post();
        newPost.setContent(post.get("description"));
        newPost.setImageUrl(post.get("uploadedUrl"));
        newPost.setCategory(post.get("catagory"));
        newPost.setUser(userRepository.findByUsername(username).orElse(null));
        newPost.setCreatedAt(LocalDateTime.now());
        System.out.println("this is the new post data"+newPost);

        postNotification.sendNotification(newPost.getUser().getId());

        return postRepository.save(newPost);
    }

    @Override
    public List<PostDto> getPostsByUsername(String username) {

        List<Post> posts = postRepository.findByUserUsernameOrderByCreatedAtDesc(username);
        return posts.stream().map(post -> {
            PostDto dto = new PostDto();
            dto.setPostId(post.getId());
            dto.setContent(post.getContent());
            dto.setImageUrl(post.getImageUrl());
            dto.setCatagory(post.getCategory());
            return dto;
        }).collect(Collectors.toList());
    }

    @Override
    public Optional<Post> getPostById(Long id) {
        return postRepository.findById(id);
    }
}

