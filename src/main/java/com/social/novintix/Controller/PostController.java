package com.social.novintix.Controller;



import com.social.novintix.Config.JwtUtils;
import com.social.novintix.Services.PostService;
import com.social.novintix.dto.CreatePostRequest;
import com.social.novintix.dto.PostResponse;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/posts")
public class PostController {

    private final PostService postService;
    private final JwtUtils jwtUtils;

    public PostController(PostService postService, JwtUtils jwtUtils) {
        this.postService = postService;
        this.jwtUtils = jwtUtils;
    }

    // Create a new post
    @PostMapping("/create")
    public ResponseEntity<?> createPost(
            @RequestBody CreatePostRequest req,
            HttpServletRequest request
    ) {
        String authHeader = request.getHeader("Authorization");

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return ResponseEntity.status(401).body("Missing or invalid token");
        }

        String token = authHeader.substring(7);
        String username = jwtUtils.extractUser(token);

        PostResponse response = postService.createPost(req, username);

        return ResponseEntity.ok(response);
    }

    // Get all posts (Feed)
    @GetMapping("/feed")
    public ResponseEntity<List<PostResponse>> getAllPosts() {
        List<PostResponse> posts = postService.getAllPosts();
        return ResponseEntity.ok(posts);
    }
}

