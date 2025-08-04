package com.socialmedia.socialmediaapp.dto;

import com.socialmedia.socialmediaapp.entities.Comments;
import com.socialmedia.socialmediaapp.entities.Like;

import java.util.List;
import java.util.Set;

public class UserFullDto {
    private Long id;
    private String username;
    private String profileImageUrl;
    private Set<String> interests;
    private List<Like> likes;
    private List<Comments> comments;
}
