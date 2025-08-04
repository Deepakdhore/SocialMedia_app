package com.socialmedia.socialmediaapp.controllers;

import com.socialmedia.socialmediaapp.dto.FullPostDto;
import com.socialmedia.socialmediaapp.dto.PostDto;
import com.socialmedia.socialmediaapp.entities.Post;
import com.socialmedia.socialmediaapp.entities.User;
import com.socialmedia.socialmediaapp.repositories.UserRepository;
import com.socialmedia.socialmediaapp.services.postServices.PostService;
import com.socialmedia.socialmediaapp.restclient.MediaClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import com.socialmedia.socialmediaapp.repositories.PostRepository;
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
    @Autowired
    private PostRepository postRepository;

    @Autowired
    private MediaClient mediaClient;
    /// //////////////////////////////////////

    @PostMapping(value = "/{username}/with-media", consumes = "multipart/form-data")
    public ResponseEntity<PostDto> createPostWithMedia(
            @PathVariable String username,
            @RequestPart("content") String content,
            @RequestPart("category") String category,
            @RequestParam("mediaType") String mediaType,
            @RequestPart(value = "file", required = false) MultipartFile mediaFile
    ) {
        System.out.println("Congrats uhit the end point @PostMapping(value = /{username}/with-media");
        try {
            // Step 1: Prepare post data
            Map<String, String> postRequest = Map.of(
                    "content", content,
                    "category", category
            );
            System.out.println("Step one Success");
            // Step 2: Create post without media
            Post newPost = postService.createPost(postRequest, username);
            System.out.println("Step 2 newPost"+newPost.toString());
            // Step 3: Upload media if present
            String mediaUrl = null;
            User user=userRepository.findByUsername(username).orElse(null);
            try {
                System.out.println("🎯 Calling MediaClient.upload with postId: " + newPost.getId());
                mediaUrl = mediaClient.uploadMedia(mediaFile, user.getId(), newPost.getId(), mediaType, category);
                System.out.println("✅ Received mediaUrl from MediaClient: " + mediaUrl);
            } catch (Exception e) {
                System.out.println("❌ Failed to call media service: " + e.getMessage());
                e.printStackTrace();
            }

            System.out.println("step 3 user and mediaUrl"+user.toString()+"\n"+mediaUrl);
            // Step 4: Update post with media URL if uploaded
            if (mediaUrl != null) {
                newPost.setImageUrl("http://localhost:8082"+mediaUrl);
                postRepository.save(newPost);
            }
            System.out.println("new post update "+newPost.toString());
            // Step 5: Build DTO response
            PostDto newPostDto = new PostDto();
            newPostDto.setPostId(newPost.getId());
            newPostDto.setContent(newPost.getContent());
            newPostDto.setCatagory(newPost.getCategory());
            newPostDto.setUser(newPost.getUser());
            newPostDto.setImageUrl(newPost.getImageUrl());
            System.out.println("postDto"+newPostDto.toString());
            return new ResponseEntity<>(newPostDto, HttpStatus.CREATED);

        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }



    /// //////////////////////////////////////

    @PostMapping("/{username}")
    public ResponseEntity<PostDto> createPost(@PathVariable String username, @RequestBody Map<String,String> post) {

        System.out.println(post);

        Post newPost= postService.createPost(post,username);

        PostDto newPostDto=new PostDto();
        newPostDto.setPostId(newPost.getId());
        newPostDto.setContent(newPost.getContent());
        newPostDto.setCatagory(newPost.getCategory());
        newPostDto.setUser(newPost.getUser());
        newPostDto.setImageUrl(newPost.getImageUrl());
        System.out.println(newPostDto+"\n"+newPostDto);
        return new ResponseEntity<>(newPostDto, HttpStatus.CREATED);

    }

    @GetMapping("/user/{username}")
    public ResponseEntity<List<FullPostDto>> getUserPosts(@PathVariable String username) {
        return new ResponseEntity<>(postService.getPostsByUsername(username), HttpStatus.OK);
    }

    @GetMapping("/{postId}")
    public ResponseEntity<Post> getPost(@PathVariable Long postId) {
        return postService.getPostById(postId)
                .map(post -> new ResponseEntity<>(post, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/category/{category}/feed")
    public ResponseEntity<List<FullPostDto>> getFeedByCategory(@PathVariable String category) {
        List<FullPostDto> feed = postService.getFeedByCategory(category);
        return ResponseEntity.ok(feed);
    }

}
