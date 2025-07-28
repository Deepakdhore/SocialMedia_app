package com.socialMedia.socialMediaApp.dto;

import com.socialMedia.socialMediaApp.entities.User;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
@Data
public class PostDto {

    private Long postId;
    private String content;

    private String imageUrl;

    private User user;

    private String catagory;
}
