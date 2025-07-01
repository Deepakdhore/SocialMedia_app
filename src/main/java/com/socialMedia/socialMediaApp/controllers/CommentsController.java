package com.socialMedia.socialMediaApp.controllers;

import com.socialMedia.socialMediaApp.dto.CommentResponse;
import com.socialMedia.socialMediaApp.entities.Comments;
import com.socialMedia.socialMediaApp.entities.User;
import com.socialMedia.socialMediaApp.repositories.PostRepository;
import com.socialMedia.socialMediaApp.repositories.UserRepository;
import com.socialMedia.socialMediaApp.services.commentsServices.CommentsServices;
import com.socialMedia.socialMediaApp.services.dtoServices.DtoServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("api/comments")
public class CommentsController {

    @Autowired
    DtoServices dtoServices;
    @Autowired
    UserRepository userRepository;
    @Autowired
    PostRepository postRepository;
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

        return dtoServices.convertCommentToDto(commentsServices.addComment(comments));
    }
}
