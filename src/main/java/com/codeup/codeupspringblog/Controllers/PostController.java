package com.codeup.codeupspringblog.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;


@Controller
class PostController {

    @GetMapping("/posts")
    public String postIndex(Model model) {
        List<Post> posts = new ArrayList<>();
        posts.add(new Post(1, "First Entry", "Lifes a bitch and then you die, so do whatever the hell you want until it's ya time ;) "));
        posts.add(new Post(2, "Second Entry", "Life is like a box of chocolates, its full of nuts!"));

        model.addAttribute("posts", posts);

        return "posts/index";
    }

    @GetMapping("/posts/{id}")
    public String individualPost(@PathVariable int id, Model model) {
        Post post = new Post(1, "First Entry", "Lifes a bitch and then you die, so do whatever the hell you want until it's ya time ;) ");


        model.addAttribute("post", post);

        return "posts/show";
    }

    @GetMapping("/posts/create")
    @ResponseBody
    public String createPostGet() {
        return "This is the view the form for creating a post page!";
    }

    @PostMapping("/posts/create")
    @ResponseBody
    public String createPostPost() {
        return "This is the post created page!";
    }



}