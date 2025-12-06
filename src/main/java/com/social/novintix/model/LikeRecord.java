package com.social.novintix.model;


import jakarta.persistence.*;

@Entity
@Table(name = "post_likes")
public class LikeRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long likeId;

    @ManyToOne
    @JoinColumn(name = "liked_by")
    private User likedBy;

    @ManyToOne
    @JoinColumn(name = "liked_post")
    private Post likedPost;

}

