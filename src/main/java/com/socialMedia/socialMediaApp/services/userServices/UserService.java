package com.socialMedia.socialMediaApp.services.userServices;

import com.socialMedia.socialMediaApp.dto.FollowResponse;
import com.socialMedia.socialMediaApp.entities.User;

import java.util.Optional;

public interface UserService {
    User createUser(User user);
    Optional<User> getUserByUsername(String username);

    FollowResponse addFollower(Long followerId,Long id);
    //User getUserById(Long userId);
}
