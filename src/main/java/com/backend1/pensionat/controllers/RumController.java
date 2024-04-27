package com.backend1.pensionat.controllers;

import com.backend1.pensionat.dtos.RumDto;
import com.backend1.pensionat.models.Bokning;
import com.backend1.pensionat.models.Rum;
import com.backend1.pensionat.repos.BokningRepo;
import com.backend1.pensionat.repos.RumRepo;
import com.backend1.pensionat.services.BokningService;
import com.backend1.pensionat.services.RumService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/rum")
public class RumController {

    private final RumRepo rumRepo;
    private final RumService rumService;
    private final BokningRepo bokningRepo;
    private final BokningService bokningService;

    @RequestMapping("/all")
    public String allRums(Model model) {
        List<RumDto> responseList = rumService.getAllRum();
        model.addAttribute("responseList", responseList);
        model.addAttribute("kat", "rum");
        model.addAttribute("titel", "Rum");
        return "/allaRum";
    }

    @RequestMapping("/sök")
    public String findRum(@RequestParam int guests, @RequestParam String startDate, @RequestParam String stopDate, Model model) {
        List<RumDto> availableRumList = new ArrayList<>();
        List<RumDto> availableRumByCapacity = rumService.getAvailableRum(guests);
        availableRumList = bokningService.getAvailableRumByDate(availableRumByCapacity);
       /* List<Object> bokningsdetaljer = new ArrayList<>();
        bokningsdetaljer.add(guests);
        bokningsdetaljer.add(startDate);
        bokningsdetaljer.add(stopDate);
        model.addAttribute("bokningsdetaljer", bokningsdetaljer);*/
        model.addAttribute("availableRumList", availableRumList);
        model.addAttribute("startDatum", startDate);
        model.addAttribute("stopDatum", stopDate);
        model.addAttribute("antal", guests);
        return "allaLedigaRum";
    }
    @RequestMapping("/boka/{id}")
    public String findRum(Model model, @PathVariable int id, @RequestParam String startDate, @RequestParam String stopDate, @RequestParam int guests) {
        model.addAttribute("rumId", id);
        model.addAttribute("guests", guests);
        model.addAttribute("startDate", startDate);
        model.addAttribute("stopDate", stopDate);

        return "redirect:/bokning/add";
    }
}
