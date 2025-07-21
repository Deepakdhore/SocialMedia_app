package com.socialMedia.socialMediaApp.controllers;

import com.socialMedia.socialMediaApp.dto.FollowResponse;
import com.socialMedia.socialMediaApp.entities.User;
import com.socialMedia.socialMediaApp.services.userServices.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;
    @PostMapping
    public ResponseEntity<?> createUser(@RequestBody User user) {
        Optional<User> existingUser = userService.getUserByUsername(user.getUsername());
        if (existingUser.isPresent()) {
            return new ResponseEntity<>("Username already exists", HttpStatus.CONFLICT);
        }

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
    @PutMapping("/{username}/bio")
    public User updateBio(@PathVariable String username, @RequestBody Map<String, String> payload) {
        String newBio = payload.get("bio");
        User updatedUser = userService.updateUserBio(username, newBio);
        return updatedUser;
    }

    @PutMapping("/{userName}/interests")
    public ResponseEntity<String> updateInterest(@PathVariable String username,@RequestBody Set<String> interests){

        User user= userService.getUserByUsername(username).orElse(null);
       user.getInterests().addAll(interests);
       User updated=userService.updateUser(user);

        return ResponseEntity.ok(username+"\n"+interests);
    }

}

