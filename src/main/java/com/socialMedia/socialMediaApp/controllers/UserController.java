package com.socialMedia.socialMediaApp.controllers;

import com.socialMedia.socialMediaApp.dto.FollowResponse;
import com.socialMedia.socialMediaApp.entities.User;
import com.socialMedia.socialMediaApp.services.userServices.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user) {
        User saved = userService.createUser(user);
        return new ResponseEntity<>(saved, HttpStatus.CREATED);
    }

    @GetMapping("/{username}")
    public ResponseEntity<User> getUser(@PathVariable String username) {
        return userService.getUserByUsername(username)
                .map(user -> new ResponseEntity<>(user, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping("/folllow/{followerUserId}/{userId}")
    public FollowResponse addFollower(@PathVariable Long followerUserId,
                                      @PathVariable Long userId){
        return  userService.addFollower(followerUserId,userId);
    }
}

