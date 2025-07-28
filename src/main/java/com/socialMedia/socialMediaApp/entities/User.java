package com.socialMedia.socialMediaApp.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import jakarta.persistence.*;
import lombok.ToString;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

    @ElementCollection
    @CollectionTable(name = "user_interests", joinColumns = @JoinColumn(name = "user_id"))
    @Column(name = "interest")
    @ToString.Exclude
    private Set<String> interests = new HashSet<>();


    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    @ToString.Exclude
    private List<Comments> comments;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    @ToString.Exclude
    private List<Like> likes;

    // ✅ Users this user is following
    @ManyToMany
    @JoinTable(
            name = "user_following",
            joinColumns = @JoinColumn(name = "follower_id"), // current user
            inverseJoinColumns = @JoinColumn(name = "following_id") // user they follow
    )
    @JsonIgnore // Prevent infinite loop in JSON response (this one prevents recursive refrence to different entities)
    @ToString.Exclude// also prevent infintie loop it stops the recursive calls to toString method of the lombok(data),
    private List<User> following;

    // ✅ Users following this user
    @ManyToMany(mappedBy = "following")
    @JsonIgnore
    @ToString.Exclude
    private List<User> followers;

}
