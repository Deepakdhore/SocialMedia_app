package com.socialMedia.socialMediaApp.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)  // Auto-incremented ID And Auto Update
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;

    private String fullName;
    private String bio;
    private String profileImageUrl;


    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Comments> comments;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Like> likes;

    // ✅ Users this user is following
    @ManyToMany
    @JoinTable(
            name = "user_following",
            joinColumns = @JoinColumn(name = "follower_id"), // current user
            inverseJoinColumns = @JoinColumn(name = "following_id") // user they follow
    )
    @JsonIgnore // Prevent infinite loop in JSON response
    private List<User> following;

    // ✅ Users following this user
    @ManyToMany(mappedBy = "following")
    @JsonIgnore
    private List<User> followers;

}
