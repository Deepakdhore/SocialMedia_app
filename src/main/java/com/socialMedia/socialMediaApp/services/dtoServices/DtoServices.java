package com.socialMedia.socialMediaApp.services.dtoServices;

import com.socialMedia.socialMediaApp.dto.CommentResponse;
import com.socialMedia.socialMediaApp.entities.Comments;
import com.socialMedia.socialMediaApp.repositories.CommentRepository;

public interface DtoServices {
    public CommentResponse convertCommentToDto(Comments comments);
}
