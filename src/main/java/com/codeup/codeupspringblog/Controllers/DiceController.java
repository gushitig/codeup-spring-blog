package com.codeup.codeupspringblog.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@Controller
class DiceController {

    @GetMapping("/roll-dice")
    public String guess() {
        return "roll-dice";
    }

    @GetMapping("/roll-dice/{n}")
    public String rollDice(@PathVariable int n, Model model) {
        String msg = "";

        model.addAttribute("guess", n);

        int diceRoll = (int) (Math.random() * 6 ) + 1;
        model.addAttribute("diceRoll", diceRoll);

        if (diceRoll == n) {
            msg = "Tadaaa! You guessed correct!";
        } else {
            msg = "Bzzztt! You guessed wrong!";
        }

        model.addAttribute("msg", msg);

        //bonus
        List<Integer> rolls = new ArrayList<>();
        for (int i = 0; i < 6; i++) {
            rolls.add((int) (Math.random() * 6 ) + 1);
        }

        model.addAttribute("rolls", rolls);

        return "answer";
    }






}
