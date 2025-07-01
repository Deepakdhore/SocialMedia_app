package com.socialMedia.socialMediaApp.services.dtoServices;

import com.socialMedia.socialMediaApp.dto.CommentResponse;
import com.socialMedia.socialMediaApp.entities.Comments;
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

