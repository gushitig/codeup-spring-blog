package com.codeup.codeupspringblog.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller //quite a few annotations w spring, metadata/configuration to be passed to spring
    //@Controller states that this class should be used to handle incoming HTTP requests from users
class HelloController {

    @GetMapping("/hello") //defines a method that should be invoked when a GET request is received for the specified URI
    @ResponseBody
    public String hello() { //tells Spring that whatever is returned from this method should be the body of our response
        return "Hello from Spring!";
    }

    @GetMapping("/hello/{name}")
    @ResponseBody
    public String sayHello(@PathVariable String name) {
        return "Hello " + name + "!";
    }
    //using variables in the path now

    @GetMapping("/hello/{name}/{last}")
    @ResponseBody
    public String sayHelloFullname(@PathVariable String name, @PathVariable String last) {
        return "Hello " + name + " " + last + "!";
    }
    //using multiple variables can use / or &


    @GetMapping("/dice")
    @ResponseBody
    public String rollDice() {
        int diceRoll = (int) (Math.random() * 6 ) + 1;
        return "Dice result:  " + diceRoll;
    }


    //just the longer version of GetMapping, this one works w POST too (well both do obv)
    @RequestMapping(path = "/increment/{number}", method = RequestMethod.GET)
    @ResponseBody
    public String addOne(@PathVariable int number) {
        return number + " plus one is " + (number + 1) + "!";
    }

    //annotations are the newer, more modern way of working w spring
    //you will still see people working in XML but this is the way to go





}
