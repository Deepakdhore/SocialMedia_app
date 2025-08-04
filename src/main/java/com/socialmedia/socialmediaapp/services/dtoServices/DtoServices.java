package com.socialmedia.socialmediaapp.services.dtoServices;

import com.socialmedia.socialmediaapp.dto.CommentResponse;
import com.socialmedia.socialmediaapp.entities.Comments;
import com.socialmedia.socialmediaapp.repositories.CommentRepository;

public interface DtoServices {
    public CommentResponse convertCommentToDto(Comments comments);
}
