package com.socialmedia.socialmediaapp.services.postServices;

import com.socialmedia.socialmediaapp.dto.CommentDto;
import com.socialmedia.socialmediaapp.dto.FullPostDto;
import com.socialmedia.socialmediaapp.dto.PostDto;
import com.socialmedia.socialmediaapp.entities.Post;
import com.socialmedia.socialmediaapp.repositories.PostRepository;
import com.socialmedia.socialmediaapp.repositories.UserRepository;
import com.socialmedia.socialmediaapp.services.notificationServices.CommentNotificationService;
import com.socialmedia.socialmediaapp.services.notificationServices.PostNotification;
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
        newPost.setContent(post.get("content"));
        newPost.setCategory(post.get("category"));
        newPost.setUser(userRepository.findByUsername(username).orElse(null));
        newPost.setCreatedAt(LocalDateTime.now());
        System.out.println("this is the new post data"+newPost);

        postNotification.sendNotification(newPost.getUser().getId());

        return postRepository.save(newPost);
    }

    @Override
    public List<FullPostDto> getPostsByUsername(String username) {

//        List<Post> posts = postRepository.findByUserUsernameOrderByCreatedAtDesc(username);
//        return posts.stream().map(post -> {
//            PostDto dto = new PostDto();
//            dto.setPostId(post.getId());
//            dto.setContent(post.getContent());
//            dto.setImageUrl(post.getImageUrl());
//            dto.setCatagory(post.getCategory());
//            return dto;
//        }).collect(Collectors.toList());
        List<FullPostDto> fullPost=postRepository.findByUserUsernameOrderByCreatedAtDesc(username)
                .stream()
                .map(this::toFullDto)
                .toList();
        return fullPost;
    }

    @Override
    public Optional<Post> getPostById(Long id) {
        return postRepository.findById(id);
    }


   

    @Override
    public List<FullPostDto> getFeedByCategory(String category) {
        return postRepository.findAllByCategoryOrderByCreatedAtDesc(category)
                .stream()
                .map(this::toFullDto)
                .toList();
    }

    private FullPostDto toFullDto(Post post) {
        FullPostDto dto = new FullPostDto();
        dto.setPostId(post.getId());
        dto.setContent(post.getContent());
        dto.setImageUrl(post.getImageUrl());
        dto.setCategory(post.getCategory());
        dto.setCreatedAt(post.getCreatedAt());
        dto.setAuthorUsername(post.getUser().getUsername());
        dto.setUserId(post.getUser().getId());

        // Likes count
        dto.setLikesCount(post.getLikes() == null ? 0 : post.getLikes().size());

        // Comments
        List<CommentDto> comments = post.getComments() == null
                ? List.of()
                : post.getComments().stream().map(c ->
                new CommentDto(
                        c.getCId(),
                        c.getComment(),
                        c.getUser().getUsername(),
                        c.getCommentedAt()
                )
        ).toList();
        dto.setComments(comments);

        return dto;
    }
}

