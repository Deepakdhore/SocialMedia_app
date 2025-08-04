package com.socialmedia.socialmediaapp.entities;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import lombok.Data;
import org.hibernate.annotations.Fetch;

import java.time.LocalDateTime;
import java.util.List;

@Table(name="comments")
@Data
@Entity
public class Comments {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cId;
    private String comment;
    private LocalDateTime commentedAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="user_id")
    @JsonIgnore
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="post_id")
    private Post post;

}
