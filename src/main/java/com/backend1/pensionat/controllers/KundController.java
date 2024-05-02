package com.backend1.pensionat.controllers;

import com.backend1.pensionat.dtos.DetailedKundDto;
import com.backend1.pensionat.models.Kund;
import com.backend1.pensionat.repos.BokningRepo;
import com.backend1.pensionat.repos.KundRepo;
import com.backend1.pensionat.services.KundService;
import com.backend1.pensionat.services.impl.KundServiceImpl;
import jakarta.validation.Valid;
import jakarta.persistence.Id;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor // la till denna istället för konstruktorn
@RequestMapping("/kund")
public class KundController {

    private final KundRepo kundRepo;
    private final BokningRepo bokningRepo;
    private final KundService kundService;
    @Autowired
    private final KundServiceImpl kundServiceImp;

    @RequestMapping("/all")
    public String getAllKunder(Model model) {
        List<DetailedKundDto> responseList = kundService.getAllKunder();
        model.addAttribute("responseList", responseList);
        model.addAttribute("kat", "kunder");
        model.addAttribute("titel", "Kund");
        return "/allaKunder";
    }

    @RequestMapping("/delete/{id}")
    public String deleteKundById(@PathVariable long id, Model model) {
        kundService.deleteKundById(id);
        return "redirect:/kund/all";
    }
    /*
        @RequestMapping("/add")
        public String addKund(Model model) {
            //kundRepo.deleteById(id);
            return "addKund"; //note
        }
    */
    @PostMapping("/add")
    public String sparaKund(@Valid @ModelAttribute("kund") DetailedKundDto kund, BindingResult result, Model model, @RequestParam String redirect ) {
        if (result.hasErrors()) {
            model.addAttribute("kat", "Lägg till ny kund");
            model.addAttribute("titel", "Kund");
            return "addKund";
        }
        kundServiceImp.spara(kund);
        return "redirect:/" + redirect;
    }

    @GetMapping("/ny")
    public String nyKundFrånKundLista(Model model) {
        model.addAttribute("kund", new DetailedKundDto());
        model.addAttribute("redirect", "kund/all");
        model.addAttribute("kat", "Lägg till ny kund");
        model.addAttribute("titel", "Kund");
        return "addKund";
    }
    @GetMapping("/nyFrånBokning")
    public String nyKundFrånBokning(Model model) {
        model.addAttribute("kund", new DetailedKundDto());
        model.addAttribute("redirect", "bokning/addkund");
        model.addAttribute("kat", "Lägg till ny kund från bokning");
        model.addAttribute("titel", "Ny kund för bokning");
        return "addKund";
    }

    @GetMapping("/redigera/{id}")
    public String visaForm(@PathVariable("id") Integer id, Model model) {
        DetailedKundDto kund = kundService.getKund(id);
        model.addAttribute("kat", "Ändra kunduppgifter");
        model.addAttribute("titel", "Kund");
        model.addAttribute("kund", kund);
        return "addKund";
    }


    public boolean checkIfKundHasBokning(long kundId){
        return bokningRepo.getKundIdList().stream().anyMatch(kund -> kund.getId() == kundId);
    }
}