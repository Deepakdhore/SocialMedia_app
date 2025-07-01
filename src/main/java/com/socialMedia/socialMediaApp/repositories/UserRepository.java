package com.socialMedia.socialMediaApp.repositories;

import com.socialMedia.socialMediaApp.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);  // 🔍 Custom Query via Method Naming
}
