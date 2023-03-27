package com.codeup.codeupspringblog.Controllers;


import com.codeup.codeupspringblog.Repositories.DogRepository;
import com.codeup.codeupspringblog.models.Dog;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@AllArgsConstructor
@Controller
@RequestMapping(path = "/dogs")
public class DogController {
    private final DogRepository dogDao;

    @GetMapping
    public String all(Model model) {

        List<Dog> dogs = dogDao.findAll();
//        dogs.add(new Dog(1, "Spot"));
//        dogs.add(new Dog(2, "Barfy"));

        model.addAttribute("dogs", dogs);

//        List<Dog> genderDogs = dogDao.findByGender("Female");
//        System.out.println(genderDogs);

        List<Dog> someDogs = dogDao.findLikeName("a");
        System.out.println(someDogs);

//        System.out.println("Finding spot by his name");
//        Dog spot = dogDao.findByName("SpotSpot");
//        System.out.println(spot);

        return "dogs/dogs";
    }

    @GetMapping("/{dogId}")
    @ResponseBody
    public String viewDog(@PathVariable long dogId) {
        Dog dog = dogDao.findById(dogId).get();

        String newName = dog.getName() + dog.getName();
        dog.setName(newName);

        dogDao.save(dog);

        System.out.println(dog);

        return "show page for a dog w id " + dogId;
    }

    @GetMapping("/create")
    @ResponseBody
    public String createDog() {
        Dog dog = new Dog();
        dog.setName("Ralph");

        dogDao.save(dog);
        return "dog created";
    }

    @Override
    public String toString() {
        return super.toString();
    }
}