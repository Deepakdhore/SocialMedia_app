package com.socialmedia.socialmediaapp.controllers;

import com.socialmedia.socialmediaapp.dto.CommentResponse;
import com.socialmedia.socialmediaapp.dto.NotificationMessage;
import com.socialmedia.socialmediaapp.entities.Comments;
import com.socialmedia.socialmediaapp.entities.User;
import com.socialmedia.socialmediaapp.repositories.PostRepository;
import com.socialmedia.socialmediaapp.repositories.UserRepository;
import com.socialmedia.socialmediaapp.services.commentsServices.CommentsServices;
import com.socialmedia.socialmediaapp.services.dtoServices.DtoServices;
import com.socialmedia.socialmediaapp.services.notificationServices.CommentNotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("api/comments")
public class CommentsController {


    @Autowired
    CommentNotificationService commentNotificationService;
    @Autowired
    DtoServices dtoServices;
    @Autowired
    UserRepository userRepository;
    @Autowired
    PostRepository postRepository;
    @Autowired
    CommentsServices commentsServices;
    @Autowired
    CommentsController(CommentsServices commentsServices)
    {
        this.commentsServices=commentsServices;
    }


    @PostMapping("/{postId}/{userId}")
    public CommentResponse postComment(@PathVariable Long userId,
                                @PathVariable Long postId,
                                @RequestBody CommentRequest commentRequest){

        Comments comments=new Comments();
        comments.setComment(commentRequest.getComment());
        comments.setCommentedAt(LocalDateTime.now());
        comments.setUser(userRepository.findById(userId).orElse(null));
        comments.setPost(postRepository.findById(postId).orElse(null));
        commentNotificationService.sendNotification(userId, comments.getUser().getId(),postId);
        return dtoServices.convertCommentToDto(commentsServices.addComment(comments));
    }
}
