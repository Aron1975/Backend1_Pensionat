package com.backend1.pensionat.controllers;

import com.backend1.pensionat.dtos.RumDto;
import com.backend1.pensionat.models.Rum;
import com.backend1.pensionat.repos.RumRepo;
import com.backend1.pensionat.services.RumService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Controller
//@RestController
@RequiredArgsConstructor
@RequestMapping("/rum")
public class RumController {

    private final RumRepo rumRepo;
    private final RumService rumService;

/*    public RumController(RumRepo rumRepo, RumService rumService) {
        this.rumRepo = rumRepo;
        this.rumService = rumService;
    }*/

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
        // List<RumDto> availableRum = rumService.getAvailableRum();
        model.addAttribute("startDatum", startDate);
        model.addAttribute("stopDatum", stopDate);
        model.addAttribute("antal", guests);
        return "allaLedigaRum";
    }
}
