package com.socialMedia.socialMediaApp.services.userServices;

import com.socialMedia.socialMediaApp.entities.User;

import java.util.Optional;

public interface UserService {
    User createUser(User user);
    Optional<User> getUserByUsername(String username);
    //User getUserById(Long userId);
}
