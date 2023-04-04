package com.codeup.codeupspringblog.Repositories;

import com.codeup.codeupspringblog.models.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {

    Post findPostById(long id);

    Post findByTitle(String title);
    //List<Post> findByTitle(String title);

}
