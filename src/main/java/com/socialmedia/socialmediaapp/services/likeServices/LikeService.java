package com.socialmedia.socialmediaapp.services.likeServices;

import com.socialmedia.socialmediaapp.entities.Like;

public interface LikeService {
    public Like addLike(Long userId,Long postId);
}
