package com.codeup.codeupspringblog.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
class PostController {

    @GetMapping("/posts")
    @ResponseBody
    public String postIndex() {
        return "This is the posts index page!";
    }

    @GetMapping("/posts/{id}")
    @ResponseBody
    public String individualPost() {
        return "This is the view an individual post page!";
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