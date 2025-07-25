package com.socialMedia.socialMediaApp.controllers;

import com.socialMedia.socialMediaApp.dto.PostDto;
import com.socialMedia.socialMediaApp.entities.Post;
import com.socialMedia.socialMediaApp.entities.User;
import com.socialMedia.socialMediaApp.repositories.UserRepository;
import com.socialMedia.socialMediaApp.services.postServices.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/posts")
public class PostController {

    @Autowired
    private PostService postService;

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/{username}")
    public ResponseEntity<PostDto> createPost(@PathVariable String username, @RequestBody Map<String,String> post) {

        System.out.println(post);

        Post newPost= postService.createPost(post);

        PostDto newPostDto=new PostDto();
        newPostDto.setContent(newPost.getContent());
        newPostDto.setUser(newPost.getUser());
        newPostDto.setImageUrl(newPost.getImageUrl());
        System.out.println(newPostDto+"\n"+newPostDto);
        return new ResponseEntity<>(newPostDto, HttpStatus.CREATED);
//        Optional<User> userOpt = userRepository.findByUsername(username);
//        if (userOpt.isEmpty()) {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
//        post.setUser(userOpt.get());
//        Post saved = postService.createPost(post);

    }

    @GetMapping("/user/{username}")
    public ResponseEntity<List<PostDto  >> getUserPosts(@PathVariable String username) {
        return new ResponseEntity<>(postService.getPostsByUsername(username), HttpStatus.OK);
    }

    @GetMapping("/{postId}")
    public ResponseEntity<Post> getPost(@PathVariable Long postId) {
        return postService.getPostById(postId)
                .map(post -> new ResponseEntity<>(post, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}
