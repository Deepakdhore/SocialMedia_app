package com.socialmedia.socialmediaapp.dto;

import lombok.Data;

import java.time.LocalDateTime;
@Data
public class NotificationMessage {
    private String receiverName;
    private String senderName;
    private String message;
    private String type;
    private LocalDateTime timestamp;
}
