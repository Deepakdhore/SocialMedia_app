package com.socialMedia.socialMediaApp.dto;

import lombok.Data;

import java.time.LocalDateTime;
@Data
public class NotificationMessage {
    private String receiverName;
    private String senderName;
    private String message;
    private String type;
    private LocalDateTime timestamp;

    // Constructors, Getters, Setters
}
