package com.backend1.pensionat.controllers;

import com.backend1.pensionat.models.Rum;
import com.backend1.pensionat.repos.RumRepo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/rum")
public class RumController {

    private final RumRepo rumRepo;

    public RumController(RumRepo rumRepo) {
        this.rumRepo = rumRepo;
    }

    @RequestMapping("/all")
    public String allRums(Model model) {
        List<Rum> responseList = rumRepo.findAll();
        model.addAttribute("responseList", responseList);
        model.addAttribute("kat", "rum");
        model.addAttribute("titel", "Rum");
        return "/allaRum";
    }

    @RequestMapping("/sök")
    public String findRum(Model model) {

        return "sökRum";
    }
}
