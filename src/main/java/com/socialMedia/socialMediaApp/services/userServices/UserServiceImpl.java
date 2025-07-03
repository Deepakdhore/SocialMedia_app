package com.socialMedia.socialMediaApp.services.userServices;

import com.socialMedia.socialMediaApp.dto.FollowResponse;
import com.socialMedia.socialMediaApp.entities.User;
import com.socialMedia.socialMediaApp.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User createUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public Optional<User> getUserByUsername(String username) {
        return userRepository.findByUsername(username);  // Custom Query
    }

    @Override
    public FollowResponse addFollower(Long followerId,Long id) {
        FollowResponse fResponse=new FollowResponse();
        User userWantToFollow= userRepository.findById(followerId).orElse(null);
        User userToBeFollowed=userRepository.findById(id).orElse(null);

        userWantToFollow.getFollowing().add(userToBeFollowed);
        userToBeFollowed.getFollowers().add(userWantToFollow);
        fResponse.setFollowerName(userWantToFollow.getUsername());
        fResponse.setUserName(userToBeFollowed.getUsername());
        userRepository.save(userWantToFollow);
        userRepository.save(userToBeFollowed);
        return fResponse;
    }

    //    @Override
//    public User getUserById(Long userId){
//        return null
//    }
}

