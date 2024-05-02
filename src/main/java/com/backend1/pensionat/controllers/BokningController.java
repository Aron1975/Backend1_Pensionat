package com.backend1.pensionat.controllers;


import com.backend1.pensionat.dtos.BokningDto;
import com.backend1.pensionat.dtos.DetailedBokningDto;
import com.backend1.pensionat.dtos.DetailedKundDto;
import com.backend1.pensionat.dtos.RumDto;
import com.backend1.pensionat.models.Bokning;
import com.backend1.pensionat.models.Kund;
import com.backend1.pensionat.models.Rum;
import com.backend1.pensionat.repos.BokningRepo;
import com.backend1.pensionat.repos.KundRepo;
import com.backend1.pensionat.repos.RumRepo;
import com.backend1.pensionat.services.BokningService;
import com.backend1.pensionat.services.KundService;
import com.backend1.pensionat.services.RumService;
import com.backend1.pensionat.services.impl.BokningServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
    private final RumService rumService;
    private final KundService kundService;
    private final RumRepo rumRepo;
    private final KundRepo kundRepo;

    @RequestMapping("/all")
    public String allBokings(Model model) {
        bokningService.deleteBokningWithoutKundId();
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

    @RequestMapping("/{id}/add")
    public String sparaBokning(@PathVariable String id, @RequestParam int antal, @RequestParam String startDatum, @RequestParam String stopDatum) {

        Long rumId = Long.parseLong(id);
        Rum rum = rumRepo.findById(rumId).get();

        LocalDate inch = LocalDate.parse(startDatum);
        LocalDate utch = LocalDate.parse(stopDatum);
        long antalDagar = DAYS.between(inch, utch);
        int antalExtraSängar = 1;
        if (antal == 1) {
            antalExtraSängar = 0;
        } else antalExtraSängar = antal - 2;
        bokningRepo.save(Bokning.builder().bokningsDatum(LocalDate.now()).startDatum(inch).slutDatum(utch).antalGäster(antal).antalExtraSängar(antalExtraSängar).totalPris(rum.getPris()*antalDagar).rum(rum).build());

        //bokningService.spara(bokning);
        return "redirect:/bokning/addkund";
    }


    @RequestMapping("/uppdatera/{id}/")
    public String uppdateraBokning(@PathVariable String id,@RequestParam int antal, @RequestParam String startDatum, @RequestParam String stopDatum, @RequestParam long bokningsId){
        LocalDate inch = LocalDate.parse(startDatum);
        LocalDate utch = LocalDate.parse(stopDatum);
        Long rumId = Long.parseLong(id);
        Rum rum = rumRepo.findById(rumId).get();
        Bokning bokning = bokningRepo.findById(bokningsId).get();
        long antalDagar = DAYS.between(inch, utch);
        bokning.setRum(rum);
        bokning.setStartDatum(inch);
        bokning.setSlutDatum(utch);
        bokning.setAntalGäster(antal);
        bokning.setAntalExtraSängar(antal-2);
        bokning.setTotalPris(rum.getPris()*antalDagar);
        bokningRepo.save(bokning);
        return "redirect:/bokning/all";
    }

    @RequestMapping("/redigera/{id}")
    public String changeBokning(@PathVariable long id, Model model){
        model.addAttribute("bokningsId", id);
        return "redigera";
    }

    @RequestMapping("/bytaRum/{id}")
    public String findRum(@PathVariable long id, @RequestParam int guests, @RequestParam String startDate, @RequestParam String stopDate, Model model) {

        if(startDate.isBlank() || startDate.isEmpty() || stopDate.isBlank() || stopDate.isEmpty()){
            return "redirect:/rum/";
        }
        LocalDate chin = LocalDate.parse(startDate);
        LocalDate chout = LocalDate.parse(stopDate);
        if(chout.isBefore(chin)||chout.isEqual(chin)){
            return "redirect:/rum/";
        }
        List<RumDto> availableRumByCapacity = rumService.getAvailableRum(guests);
        LocalDate startDatum = LocalDate.parse(startDate);
        LocalDate stopDatum = LocalDate.parse(stopDate);
        List<RumDto> availableRumList = bokningService.getAvailableRumByDate(availableRumByCapacity, startDatum, stopDatum);
        model.addAttribute("bokningsId", id);
        model.addAttribute("availableRumList", availableRumList);
        model.addAttribute("startDatum", startDate);
        model.addAttribute("stopDatum", stopDate);
        model.addAttribute("antal", guests);
        return "bytaRum";
    }

    @RequestMapping("/delete/{id}")
    public String deleteBokningById(@PathVariable long id, Model model) {
        //Bokning bokningToDelete = bokningService.findBokningById(id);
        bokningRepo.deleteById(id);

        return "redirect:/bokning/all";
    }
}
