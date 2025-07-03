package com.socialMedia.socialMediaApp.services.commentsServices;

import com.socialMedia.socialMediaApp.entities.Comments;
import com.socialMedia.socialMediaApp.repositories.CommentRepository;
import com.socialMedia.socialMediaApp.services.notificationServices.CommentNotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CommentsServicesImpl implements CommentsServices{

    private final CommentRepository repository;

    @Autowired
    public CommentsServicesImpl(CommentRepository repository) {
        this.repository = repository;
    }

    @Autowired
    private CommentNotificationService commentNotificationService;
    @Override
    public Comments addComment(Comments comment) {
        commentNotificationService.sendNotification(comment.getUser().getId(), comment.getPost().getUser().getId(),comment.getPost().getId());
        return repository.save(comment);
    }

    @Override
    public List<Comments> getAllComments() {
        return repository.findAll();
    }

    @Override
    public List<Comments> getByUserId(Long userId) {
        return repository.findByUser_IdOrderByCommentedAt(userId);
    }

    @Override
    public List<Comments> getByPostId(Long postId) {
        return repository.findByPost_IdOrderByCommentedAt(postId);
    }

    @Override
    public List<Comments> getByUserIdPostId(Long userId, Long postId) { return repository.findByUser_IdAndPost_IdOrderByCommentedAt(userId,postId); }
}
