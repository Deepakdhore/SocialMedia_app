package com.socialmedia.socialmediaapp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;
@Data
@AllArgsConstructor
public class CommentDto {
    private Long commentId;
    private String text;
    private String username;
    private LocalDateTime commentedAt;
}