package com.codeup.codeupspringblog.Controllers;

import com.codeup.codeupspringblog.Repositories.DogRepository;
import com.codeup.codeupspringblog.Repositories.PostRepository;
import com.codeup.codeupspringblog.Repositories.UserRepository;
import com.codeup.codeupspringblog.Services.EmailService;
import com.codeup.codeupspringblog.models.Dog;
import com.codeup.codeupspringblog.models.Post;
import com.codeup.codeupspringblog.models.User;
import lombok.AllArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Controller
class PostController {
    private final PostRepository postDao;
    private final UserRepository userDao;
    private final EmailService emailService;

    @GetMapping("/posts")
    public String postIndex(Model model) {
        List<Post> posts = postDao.findAll();
        model.addAttribute("posts", posts);
        User user = userDao.getReferenceById(5L);
        model.addAttribute("user", user);

        return "posts/index";
    }

    @GetMapping("/posts/{id}")
    public String individualPost(@PathVariable long id, Model model) {
        Post post = postDao.findById(id).get();
        model.addAttribute("post", post);


        return "posts/show";
    }

    @GetMapping("/posts/create")
    public String createPostGet(Model model) {
        model.addAttribute("post", new Post());
        Post post = new Post();
        post.setTitle("Title here");
        post.setBody("Body here");
        model.addAttribute("post", post);

        return "posts/create";
    }

    @PostMapping("/posts/create")
    public String createPost(@ModelAttribute Post post) {
        /*Post post = new Post();
        post.setTitle(title);
        post.setBody(body);*/
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        post.setBlogger(user);

        postDao.save(post);

        emailService.prepareAndSend(post, "A post has been created" + post.getTitle(), post.toString());

        return "redirect:/posts";
    }

    @GetMapping("/posts/{id}/edit")
    public String showEditForm(@PathVariable long id, Model model) {
        model.addAttribute("post", postDao.findPostById(id));
        return "posts/create";
    }

    /*private User getLoggedInUser() {
        User blogger = new User();
        try {
            blogger = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        } catch {
            blogger = "";
        }
        return blogger;
    }*/



}