package com.socialMedia.socialMediaApp.controllers;

import jakarta.persistence.Entity;
import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class CommentRequest {
    private String comment;
}
