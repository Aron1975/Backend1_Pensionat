package com.backend1.pensionat.controllers;

import com.backend1.pensionat.models.Kund;
import com.backend1.pensionat.repos.BokningRepo;
import com.backend1.pensionat.repos.KundRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

//@Controller
@RestController
@RequiredArgsConstructor
@RequestMapping("/kund")
public class KundController {

    private final KundRepo kundRepo;
    private final BokningRepo bokningRepo;

 /*   public KundController(KundRepo kundRepo, BokningRepo bokningRepo) {
        this.kundRepo = kundRepo;
        this.bokningRepo = bokningRepo;
    }*/

    @RequestMapping("/all")
    public String allKund(Model model) {
        List<Kund> responseList = kundRepo.findAll();
        model.addAttribute("responseList", responseList);
        model.addAttribute("kat", "kunder");
        model.addAttribute("titel", "Kund");
        return "/allaKunder";
    }

    @RequestMapping("/delete/{id}")
    public String deleteKundById(@PathVariable long id, Model model) {
        Kund kundToDelete = kundRepo.findById(id).get();
        if(!checkIfKundHasBokning(id)) {
            kundRepo.deleteById(id);
        }
        return "redirect:/kund/all";
    }

    @RequestMapping("/add")
    public String addKund(Model model) {
        //kundRepo.deleteById(id);
        return "addKund";
    }

    public boolean checkIfKundHasBokning(long kundId){
        return bokningRepo.getKundIdList().stream().anyMatch(kund -> kund.getId() == kundId);
    }
}
