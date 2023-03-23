package com.codeup.codeupspringblog.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
class HomeController {

    @GetMapping("/")
    @ResponseBody
    public String sayHello() {
        return "This is the landing page!";
    }

}

