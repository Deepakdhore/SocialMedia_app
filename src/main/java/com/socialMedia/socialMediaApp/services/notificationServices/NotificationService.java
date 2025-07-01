package com.socialMedia.socialMediaApp.services.notificationServices;

import com.socialMedia.socialMediaApp.dto.NotificationMessage;
import com.socialMedia.socialMediaApp.entities.User;
import com.socialMedia.socialMediaApp.repositories.UserRepository;
import com.socialMedia.socialMediaApp.services.userServices.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class NotificationService {

    @Autowired
    private SimpMessagingTemplate messagingTemplate;
    @Autowired
    private UserRepository userRepository;

    public void sendNotification(Long senderId,Long receiverId, String message) {
        NotificationMessage notification = new NotificationMessage();

        String senderName = userRepository.findById(senderId)
                .orElseThrow(() -> new RuntimeException("Sender not found"))
                .getUsername();

        String receiverName = userRepository.findById(receiverId)
                .orElseThrow(() -> new RuntimeException("Receiver not found"))
                .getUsername();

        notification.setReceiverName(receiverName);
        notification.setSenderName(senderName);
        notification.setMessage(message);
        notification.setType("LIKE");
        notification.setTimestamp(LocalDateTime.now());

        messagingTemplate.convertAndSend("/topic/notify/" + receiverId, notification);
    }
}