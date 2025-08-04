package com.socialmedia.socialmediaapp.services.userServices;

import com.socialmedia.socialmediaapp.dto.FollowResponse;
import com.socialmedia.socialmediaapp.entities.User;

import java.util.Optional;

public interface UserService {
    User createUser(User user);
    Optional<User> getUserByUsername(String username);

    FollowResponse addFollower(Long followerId,Long id);

    User updateUserBio(String username, String newBio);

    User updateUser(User user);

    FollowResponse removeFollower(Long followerUserId, Long userId);
    //User getUserById(Long userId);
}
