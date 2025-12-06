package com.social.novintix.repo;

import com.social.novintix.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post,String> {

}
