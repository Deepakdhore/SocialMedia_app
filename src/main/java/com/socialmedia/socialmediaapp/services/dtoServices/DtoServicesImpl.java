package com.socialmedia.socialmediaapp.services.dtoServices;

import com.socialmedia.socialmediaapp.dto.CommentResponse;
import com.socialmedia.socialmediaapp.entities.Comments;
import org.springframework.stereotype.Service;

@Service
public class DtoServicesImpl implements DtoServices{

    @Override
    public CommentResponse convertCommentToDto(Comments comments){
        CommentResponse dto=new CommentResponse();
        dto.setId(comments.getCId());
        dto.setComment(comments.getComment());
        dto.setCommentedAt(comments.getCommentedAt());
        dto.setUsername(comments.getUser().getUsername());
        return dto;
    }

    }

