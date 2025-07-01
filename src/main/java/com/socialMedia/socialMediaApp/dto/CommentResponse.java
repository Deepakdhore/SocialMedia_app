package com.socialMedia.socialMediaApp.dto;

import lombok.Data;

import java.time.LocalDateTime;
@Data
public class CommentResponse {
    private Long id;
    private String comment;
    private String username;
    private LocalDateTime commentedAt;
}
