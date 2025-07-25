package com.socialMedia.socialMediaApp.controllers;

import com.socialMedia.socialMediaApp.dto.FollowResponse;
import com.socialMedia.socialMediaApp.dto.ProfileImageDto;
import com.socialMedia.socialMediaApp.dto.UserDTO;
import com.socialMedia.socialMediaApp.entities.User;
import com.socialMedia.socialMediaApp.repositories.UserRepository;
import com.socialMedia.socialMediaApp.services.userServices.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository userRepository;
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
    public ResponseEntity<String> addFollower(@PathVariable Long followerUserId,
                                              @PathVariable Long userId){
                FollowResponse res=userService.addFollower(followerUserId,userId);
                if(res!=null)
                {
                    return new ResponseEntity<>("Username already following", HttpStatus.CONFLICT);
                }
                return  new ResponseEntity<>("Succesfully followed the user",HttpStatus.CREATED);
    }
    @PutMapping("/{username}/bio")
    public String updateBio(@PathVariable String username, @RequestBody Map<String, String> payload) {
        String newBio = payload.get("bio");
        User updatedUser = userService.updateUserBio(username, newBio);
        return updatedUser.getBio();
    }

    @PutMapping("/{userName}/interests")
    public ResponseEntity<String> updateInterest(@PathVariable String username,@RequestBody Set<String> interests){

        User user= userService.getUserByUsername(username).orElse(null);
       user.getInterests().addAll(interests);
       User updated=userService.updateUser(user);

        return ResponseEntity.ok(username+"\n"+interests);
    }

    @PutMapping("/{userName}/profileImage")
    public ResponseEntity<String> updateProfile(@PathVariable String userName,@RequestBody ProfileImageDto profileImageUrl){
        User user = userService.getUserByUsername(userName).orElse(null);
        String mediaUrl= profileImageUrl.getProfileImageUrl();
        System.out.println("this is profile image irl being passed using put function = "+mediaUrl);
        user.setProfileImageUrl(mediaUrl);
        User updatated=userService.updateUser(user);
        return ResponseEntity.ok(updatated.getProfileImageUrl());
    }

    @GetMapping("/search")
    public ResponseEntity<List<UserDTO>> searchUsers(@RequestParam String query) {
        List<User> matchedUsers = userRepository.searchUsersByPartialUsername(query);

        // Convert to DTOs (optional, but recommended)
        List<UserDTO> userDTOs = matchedUsers.stream()
                .map(user -> new UserDTO(user.getId(), user.getUsername(), user.getProfileImageUrl()))
                .collect(Collectors.toList());

        return ResponseEntity.ok(userDTOs);
    }
}

