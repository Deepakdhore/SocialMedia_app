package com.socialMedia.socialMediaApp.services.likeServices;

import com.socialMedia.socialMediaApp.entities.Like;
import com.socialMedia.socialMediaApp.repositories.LikeRepository;
import com.socialMedia.socialMediaApp.services.postServices.PostService;
import com.socialMedia.socialMediaApp.repositories.UserRepository;
import com.socialMedia.socialMediaApp.services.notificationServices.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class LikeServiceImpl implements LikeService{

    @Autowired
    NotificationService notificationService;
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

        Like like=new Like();
        like.setUser(userRepository.findById(userId).orElse(null));
        like.setPost(postService.getPostById(postId).orElse(null));


        Long receiverId=like.getPost().getUser().getId();
        notificationService.sendNotification(userId,receiverId, " liked your post!");

        return likeRepository.save(like);
    }


}
