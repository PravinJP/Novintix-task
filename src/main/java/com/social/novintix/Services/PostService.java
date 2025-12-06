package com.social.novintix.Services;



import com.social.novintix.dto.CreatePostRequest;
import com.social.novintix.dto.PostResponse;
import com.social.novintix.model.Post;
import com.social.novintix.model.User;

import com.social.novintix.repo.PostRepository;
import com.social.novintix.repo.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostService {

    private final PostRepository postRepo;
    private final UserRepository userRepo;

    public PostService(PostRepository postRepo, UserRepository userRepo) {
        this.postRepo = postRepo;
        this.userRepo = userRepo;
    }

    public PostResponse createPost(CreatePostRequest req, String username) {

        User author = userRepo.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Post p = new Post();
        p.setMessage(req.getContent());
        p.setAuthor(author);

        Post saved = postRepo.save(p);

        return new PostResponse(
                saved.getPostId(),
                saved.getMessage(),
                author.getDisplayName(),
                saved.getCreatedAt()
        );
    }

    public List<PostResponse> getAllPosts() {
        return postRepo.findAllByOrderByCreatedAtDesc()
                .stream()
                .map(p -> new PostResponse(
                        p.getPostId(),
                        p.getMessage(),
                        p.getAuthor().getDisplayName(),
                        p.getCreatedAt()
                ))
                .collect(Collectors.toList());
    }
}

