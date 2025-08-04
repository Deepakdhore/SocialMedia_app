package com.socialmedia.socialmediaapp.controllers;

import com.socialmedia.socialmediaapp.entities.Like;
//import com.socialmedia.socialmediaapp.repositories.PostRepository;
import com.socialmedia.socialmediaapp.services.likeServices.LikeService;
//import com.socialmedia.socialmediaapp.services.postServices.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/like")
public class LikeController {

    @Autowired
    private LikeService likeService;

    @PostMapping("/{userId}/{postId}")
    public Like addLike(@PathVariable Long userId,@PathVariable Long postId) {
        Like like = likeService.addLike(userId, postId);
        if (like != null)
            return like;
        else
        {
            System.out.println("Already Liked");
            return null;
        }
    }

}
