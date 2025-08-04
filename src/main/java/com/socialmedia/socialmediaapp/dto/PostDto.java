package com.socialmedia.socialmediaapp.dto;

import com.socialmedia.socialmediaapp.entities.User;
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
