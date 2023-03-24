package com.codeup.codeupspringblog.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
//@ResponseBody - take this out so we can have the html page be the response
    //this will make the home controller look for an html file called home
class HomeController {

    @GetMapping("/home/{personName}")
    public String welcome(@RequestParam(name = "age") int age,
                          @PathVariable String personName,
                          Model model) {
        model.addAttribute("name", "Bob Smith");
        //this is called dependency injection
        model.addAttribute("personName", personName);
        model.addAttribute("personAge", age);
        return "home/home"; //if in a directory, dont need to put the file extension but you can if you wish
    }

}

