package com.backend1.pensionat.controllers;


import com.backend1.pensionat.models.Bokning;
import com.backend1.pensionat.repos.BokningRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


//@Controller
@RestController
@RequiredArgsConstructor
@RequestMapping("/bokning")
public class BokningController {

    private final BokningRepo bokningRepo;

/*    public BokningController(BokningRepo bokningRepo) {
        this.bokningRepo = bokningRepo;
    } */

    @RequestMapping("/all")
    public String allBokings(Model model) {
        List<Bokning> responseList = bokningRepo.findAll();
        model.addAttribute("responseList", responseList);
        model.addAttribute("kat", "bokningar");
        model.addAttribute("titel", "Bokning");
        return "/allaBokningar";
    }
}
