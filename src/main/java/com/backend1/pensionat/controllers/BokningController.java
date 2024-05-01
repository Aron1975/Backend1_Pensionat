package com.backend1.pensionat.controllers;


import com.backend1.pensionat.dtos.BokningDto;
import com.backend1.pensionat.dtos.DetailedBokningDto;
import com.backend1.pensionat.dtos.DetailedKundDto;
import com.backend1.pensionat.models.Bokning;
import com.backend1.pensionat.models.Kund;
import com.backend1.pensionat.models.Rum;
import com.backend1.pensionat.repos.BokningRepo;
import com.backend1.pensionat.repos.KundRepo;
import com.backend1.pensionat.repos.RumRepo;
import com.backend1.pensionat.services.BokningService;
import com.backend1.pensionat.services.KundService;
import com.backend1.pensionat.services.impl.BokningServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.List;

import static java.time.temporal.ChronoUnit.DAYS;


@Controller
@RequiredArgsConstructor
@RequestMapping("/bokning")
public class BokningController {

    private final BokningRepo bokningRepo;
    private final BokningService bokningService;
    private final BokningServiceImpl bokningServiceImpl;
    private final KundService kundService;
    private final RumRepo rumRepo;
    private final KundRepo kundRepo;

    @RequestMapping("/all")
    public String allBokings(Model model) {
        List<Bokning> bokning = bokningRepo.findAll();
        bokning.forEach((b) -> {
            if(b.getKund() == null){
                Long j = b.getId();
                bokningRepo.deleteById(j);
            }
        });
        List<DetailedBokningDto> responseList = bokningService.getAllBokningar();
        model.addAttribute("responseList", responseList);
        model.addAttribute("kat", "bokningar");
        model.addAttribute("titel", "Bokning");
        return "/allaBokningar";  //testing igen
    }
    @RequestMapping("/addkund/{id}")
    public String uppdateraBokning(@PathVariable String id){
        List<BokningDto> responseList = bokningService.getAllBokningar2();
        BokningDto d = responseList.get(responseList.size() -1);
        String bokningIdString = String.valueOf(d.getId());
        Long bokningsId = Long.parseLong(bokningIdString);
        Long kundId = Long.parseLong(id);
        Bokning bokning = bokningRepo.findById(bokningsId).get();
        Kund kund = kundRepo.findById(kundId).get();
        bokning.setKund(kund);
        bokningRepo.save(bokning);
        return "redirect:/bokning/all";
    }
    @RequestMapping("/addkund")
    public String sparaBokningTillKund(Model model){
        List<DetailedKundDto> responseList = kundService.getAllKunder();
        model.addAttribute("responseList", responseList);
        model.addAttribute("kat", "kund");
        model.addAttribute("titel", "Kunder");
        return "bokaKund";
    }

    /*@ResponseBody
    @RequestMapping("/confirmation")
    public void bekraftaBokning(@PathVariable String id* Kund kund){

        Long kundId = 1L;
        Integer kund_id = 1;
        //Long kundId = Long.parseLong(id);
        List<DetailedBokningDto> responseList = bokningService.getAllBokningar();
        DetailedBokningDto d = responseList.get(responseList.size() -1);
        String bokningIdString = String.valueOf(d.getId());
        Long bokningId = 3002L/*Long.parseLong(bokningIdString);


        //return "Test" + bokningId + " " /*+ kundDto.getId();
    }*/

    @RequestMapping("/{id}/add")
    public String sparaBokning(@PathVariable String id, @RequestParam int antal, @RequestParam String startDatum, @RequestParam String stopDatum) {

        Long rumId = Long.parseLong(id);
        Rum rum = rumRepo.findById(rumId).get();

        LocalDate inch = LocalDate.parse(startDatum);
        LocalDate utch = LocalDate.parse(stopDatum);
        long antalDagar = DAYS.between(inch, utch);
        bokningRepo.save(Bokning.builder().bokningsDatum(LocalDate.now()).startDatum(inch).slutDatum(utch).antalGäster(antal).antalExtraSängar(antal-2).totalPris(rum.getPris()*antalDagar).rum(rum).build());

        //bokningService.spara(bokning);
        return "redirect:/bokning/addkund";
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
