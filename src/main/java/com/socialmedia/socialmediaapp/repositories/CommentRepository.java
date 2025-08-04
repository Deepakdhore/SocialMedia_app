package com.socialmedia.socialmediaapp.repositories;

import com.socialmedia.socialmediaapp.entities.Comments;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comments, Long> {

    List<Comments> findByUser_IdOrderByCommentedAt(Long userId);

    List<Comments> findByPost_IdOrderByCommentedAt(Long postId);

    List<Comments> findByUser_IdAndPost_IdOrderByCommentedAt(Long userId, Long postId);
}

