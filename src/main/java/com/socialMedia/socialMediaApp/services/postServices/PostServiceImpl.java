package com.socialMedia.socialMediaApp.services.postServices;

import com.socialMedia.socialMediaApp.entities.Post;
import com.socialMedia.socialMediaApp.repositories.PostRepository;
import com.socialMedia.socialMediaApp.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class PostServiceImpl implements PostService {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public Post createPost(Post post) {
        post.setCreatedAt(LocalDateTime.now());
        return postRepository.save(post);
    }

    @Override
    public List<Post> getPostsByUsername(String username) {
        return postRepository.findByUserUsernameOrderByCreatedAtDesc(username);
    }

    @Override
    public Optional<Post> getPostById(Long id) {
        return postRepository.findById(id);
    }
}

