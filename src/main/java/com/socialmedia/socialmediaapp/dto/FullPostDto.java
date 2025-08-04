package com.socialmedia.socialmediaapp.dto;

import com.socialmedia.socialmediaapp.entities.User;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class FullPostDto {
    private Long postId;
    private String content;
    private String imageUrl;
    private String category;
    private LocalDateTime createdAt;
    private String authorUsername;
    private int likesCount;
    private List<CommentDto> comments;
    private Long userId;
}