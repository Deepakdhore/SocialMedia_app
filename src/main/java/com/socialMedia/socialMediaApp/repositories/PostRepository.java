package com.socialMedia.socialMediaApp.repositories;

import com.socialMedia.socialMediaApp.entities.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findByUserUsernameOrderByCreatedAtDesc(String username);
}
