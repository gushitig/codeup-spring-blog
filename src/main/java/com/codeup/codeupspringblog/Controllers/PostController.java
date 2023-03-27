package com.codeup.codeupspringblog.Controllers;

import com.codeup.codeupspringblog.Repositories.DogRepository;
import com.codeup.codeupspringblog.Repositories.PostRepository;
import com.codeup.codeupspringblog.models.Dog;
import com.codeup.codeupspringblog.models.Post;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Controller
class PostController {
    private final PostRepository postDao;

    @GetMapping("/posts")
    public String postIndex(Model model) {
        List<Post> posts = postDao.findAll();
        model.addAttribute("posts", posts);

        return "posts/index";
    }

    @GetMapping("/posts/{id}")
    public String individualPost(@PathVariable long id, Model model) {
        Post post = postDao.findById(id).get();
        model.addAttribute("post", post);

        return "posts/show";
    }

    @GetMapping("/posts/create")
    public String createPostGet() {

        return "posts/create";
    }

    @PostMapping("/posts/create")
    public String createPost(@RequestParam(name="titleInput") String titleInput, @RequestParam(name="bodyInput") String bodyInput) {
        Post post = new Post();
        post.setTitle(titleInput);
        post.setBody(bodyInput);

        postDao.save(post);

        return "redirect:/posts";
    }




}