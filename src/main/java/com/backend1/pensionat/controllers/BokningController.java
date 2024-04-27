package com.backend1.pensionat.controllers;


import com.backend1.pensionat.dtos.DetailedBokningDto;
import com.backend1.pensionat.models.Bokning;
import com.backend1.pensionat.repos.BokningRepo;
import com.backend1.pensionat.services.BokningService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@Controller
@RequiredArgsConstructor
@RequestMapping("/bokning")
public class BokningController {

    private final BokningRepo bokningRepo;
    private final BokningService bokningService;

    @RequestMapping("/all")
    public String allBokings(Model model) {
        List<DetailedBokningDto> responseList = bokningService.getAllBokningar();
        model.addAttribute("responseList", responseList);
        model.addAttribute("kat", "bokningar");
        model.addAttribute("titel", "Bokning");
        return "/allaBokningar";  //testing igen
    }

    @RequestMapping("/new")
    public String newBoking(Model model) {
        List<Bokning> responseList = bokningRepo.findAll();
        model.addAttribute("responseList", responseList);
        model.addAttribute("kat", "bokningar");
        model.addAttribute("titel", "Bokning");
        return "redirect:/bokning/all";  //testing igen
    }
}
