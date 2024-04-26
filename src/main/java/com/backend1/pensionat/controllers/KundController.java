package com.backend1.pensionat.controllers;

import com.backend1.pensionat.dtos.DetailedKundDto;
import com.backend1.pensionat.models.Kund;
import com.backend1.pensionat.repos.BokningRepo;
import com.backend1.pensionat.repos.KundRepo;
import com.backend1.pensionat.services.KundService;
import com.backend1.pensionat.services.impl.KundServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
        Kund kundToDelete = kundRepo.findById(id).get();
        if(!checkIfKundHasBokning(id)) {
            kundRepo.deleteById(id);
        }
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
        public String sparaKund(DetailedKundDto kund) {
        kundServiceImp.spara(kund);
              //  return "redirect:/kund/all";
        return "redirect:/kund/all";


    }

    @GetMapping("/ny")
    public String nyKund(Model model) {
        model.addAttribute("kund", new DetailedKundDto());
        return "addKund";


    }

    @GetMapping("/redigera/{id}")
    public String visaForm(@PathVariable("id") Integer id, Model model) {
        DetailedKundDto kund = kundServiceImp.getKund(id);
        model.addAttribute("kund", kund);
        return "addkund";
    }




    public boolean checkIfKundHasBokning(long kundId){
        return bokningRepo.getKundIdList().stream().anyMatch(kund -> kund.getId() == kundId);
    }
}
