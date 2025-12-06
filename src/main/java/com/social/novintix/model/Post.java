package com.social.novintix.model;


import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "user_posts")
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long postId;

    @Column(nullable = false, length = 400)
    private String text;

    private LocalDateTime postedOn;

    @ManyToOne
    @JoinColumn(name = "creator_id")
    private User creator;


}
