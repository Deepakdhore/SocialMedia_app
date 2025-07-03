package com.socialMedia.socialMediaApp.services.notificationServices;

import com.socialMedia.socialMediaApp.dto.NotificationMessage;
import com.socialMedia.socialMediaApp.repositories.UserRepository;
import com.socialMedia.socialMediaApp.services.postServices.PostServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Objects;

@Service
public class CommentNotificationService {
    @Autowired
    SimpMessagingTemplate messagingTemplate;
    @Autowired
    UserRepository userRepository;
    @Autowired
    PostServiceImpl postService;
    public void sendNotification(Long senderId,Long receiverId,Long postId) {
        NotificationMessage notification = new NotificationMessage();

        String senderName = userRepository.findById(senderId)
                .orElseThrow(() -> new RuntimeException("Sender not found"))
                .getUsername();

        String receiverName = userRepository.findById(receiverId)
                .orElseThrow(() -> new RuntimeException("Receiver not found"))
                .getUsername();
        String postName= (postService.getPostById(postId).orElse(null)).getContent();
        notification.setReceiverName(receiverName);
        notification.setSenderName(senderName);
        notification.setMessage("new Comment on post[ "+postName+" ]");
        notification.setType("Comment");
        notification.setTimestamp(LocalDateTime.now());

        messagingTemplate.convertAndSend("/topic/notify/" + receiverId, notification);
    }
}
