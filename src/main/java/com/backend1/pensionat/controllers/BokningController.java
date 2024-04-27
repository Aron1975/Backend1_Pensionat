package com.backend1.pensionat.controllers;


import com.backend1.pensionat.dtos.DetailedBokningDto;
import com.backend1.pensionat.dtos.DetailedKundDto;
import com.backend1.pensionat.models.Bokning;
import com.backend1.pensionat.models.Kund;
import com.backend1.pensionat.models.Rum;
import com.backend1.pensionat.repos.BokningRepo;
import com.backend1.pensionat.repos.KundRepo;
import com.backend1.pensionat.repos.RumRepo;
import com.backend1.pensionat.services.BokningService;
import com.backend1.pensionat.services.impl.BokningServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;


@Controller
@RequiredArgsConstructor
@RequestMapping("/bokning")
public class BokningController {

    private final BokningRepo bokningRepo;
    private final BokningService bokningService;
    private final BokningServiceImpl bokningServiceImpl;
    private final RumRepo rumRepo;
    private final KundRepo kundRepo;

    @RequestMapping("/all")
    public String allBokings(Model model) {
        List<DetailedBokningDto> responseList = bokningService.getAllBokningar();
        model.addAttribute("responseList", responseList);
        model.addAttribute("kat", "bokningar");
        model.addAttribute("titel", "Bokning");
        return "/allaBokningar";  //testing igen
    }

 /*   @RequestMapping("/new")
    public String newBoking(Model model) {
        List<Bokning> responseList = bokningRepo.findAll();
        model.addAttribute("responseList", responseList);
        model.addAttribute("kat", "bokningar");
        model.addAttribute("titel", "Bokning");
        return "redirect:/bokning/all";  //testing igen
    }*/

    @RequestMapping("/add/{id}")
    public String sparaBokning(@PathVariable Long id, @RequestParam String startDatum, @RequestParam String stopDatum, @RequestParam int antal, Model model) {

        Rum rum = rumRepo.findById(id).get();
        Kund kund = kundRepo.findById(id).get();
        int antalDagar = 4;
        LocalDate inch = LocalDate.parse(startDatum);
        LocalDate utch = LocalDate.parse(stopDatum);
        bokningRepo.save(Bokning.builder().bokningsDatum(LocalDate.now()).startDatum(inch).startDatum(utch).antalGäster(antal).antalExtraSängar(antal-2).totalPris(rum.getPris()*antalDagar).kund(kund).rum(rum).build());

        //bokningService.spara(bokning);
        return "redirect:/bokning/all";
    }

    @RequestMapping("/delete/{id}")
    public String deleteBokningById(@PathVariable long id, Model model) {
        //Bokning bokningToDelete = bokningService.findBokningById(id);
        bokningRepo.deleteById(id);

        return "redirect:/bokning/all";
    }

    @GetMapping("/redigera/{id}")
    public String visaForm(@PathVariable("id") Integer id, Model model) {
        //DetailedBokningDto bokning = bokningServiceImpl.getBokning(id);
        model.addAttribute("kat", "Ändra bokning");
        model.addAttribute("titel", "Bokning");
        //model.addAttribute("bokning", bokning);
        return "redirect:/bokning/all";
    }
}
