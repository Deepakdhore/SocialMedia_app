package com.socialMedia.socialMediaApp.services.notificationServices;

import com.socialMedia.socialMediaApp.dto.NotificationMessage;
import com.socialMedia.socialMediaApp.entities.User;
import com.socialMedia.socialMediaApp.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
@Service
public class PostNotification {
    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    @Autowired
    private UserRepository userRepository;
    public void sendNotification(Long posOwenerUserId){
        NotificationMessage notification =new NotificationMessage();

        String postOwnerName= userRepository.findById(posOwenerUserId)
                .orElseThrow(()-> new RuntimeException("Sender not found"))
                .getUsername();

        List<User> followers=userRepository.findById(posOwenerUserId)
                .orElseThrow(()-> new RuntimeException("Sender not found"))
                .getFollowers();

        for(User follower:followers){
            notification.setSenderName(postOwnerName);
            notification.setMessage("!!new Post from "+postOwnerName);
            notification.setReceiverName(follower.getUsername());
            notification.setType("NewPost");
            notification.setTimestamp(LocalDateTime.now());
            messagingTemplate.convertAndSend("/topic/notify/" + follower.getId(), notification);
        }
    }
}
