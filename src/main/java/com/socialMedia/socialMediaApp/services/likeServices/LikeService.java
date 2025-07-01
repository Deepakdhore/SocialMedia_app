package com.socialMedia.socialMediaApp.services.likeServices;

import com.socialMedia.socialMediaApp.entities.Like;

public interface LikeService {
    public Like addLike(Long userId,Long postId);
}
