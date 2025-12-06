package com.social.novintix.repo;


import com.social.novintix.model.Post;
import com.social.novintix.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {

    List<Post> findByAuthor(User user);

    List<Post> findAllByOrderByCreatedAtDesc();
}

