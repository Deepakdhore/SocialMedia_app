package com.socialmedia.socialmediaapp.services.likeServices;

import com.socialmedia.socialmediaapp.entities.Like;
import com.socialmedia.socialmediaapp.repositories.LikeRepository;
import com.socialmedia.socialmediaapp.services.postServices.PostService;
import com.socialmedia.socialmediaapp.repositories.UserRepository;
import com.socialmedia.socialmediaapp.services.notificationServices.LikeNotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class LikeServiceImpl implements LikeService{

    @Autowired
    LikeNotificationService notificationService;
    @Autowired
    LikeRepository likeRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    PostService postService;
    @Override
    public Like addLike(Long userId,Long postId) {

        if(likeRepository.existsByUser_IdAndPost_Id(userId,postId)) {
            //Like like = likeRepository.findByUser_IdAndPost_Id(userId, postId).orElse(null);
            return null;
        }
        try {
            postService.getPostById(postId);
        }catch (Exception e)
        {
            System.out.println("the post u r searching is null");
            throw new RuntimeException(e);
        }
        Like like=new Like();
        like.setUser(userRepository.findById(userId).orElse(null));
        like.setPost(postService.getPostById(postId).orElse(null));


        Long receiverId=like.getPost().getUser().getId();
        notificationService.sendNotification(userId,receiverId, " liked your post!");

        return likeRepository.save(like);
    }


}
