//package com.socialMedia.socialMediaApp.controllers;
//
//import com.socialMedia.socialMediaApp.services.notificationServices.NotificationService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Repository;
//import org.springframework.web.bind.annotation.*;
//
//@RestController
//@RequestMapping("/test")
//public class NotificationTestController {
//    @Autowired
//    private NotificationService notificationService;
//
//    @PostMapping("/notify/{userId}")
//    public String testNotify(@PathVariable Long userId, @RequestBody String message) {
//        notificationService.sendNotification(userId, message);
//        return "Notification sent to user: " + userId;
//    }
//}
