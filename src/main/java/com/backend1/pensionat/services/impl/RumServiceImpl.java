package com.backend1.pensionat.services.impl;


import com.backend1.pensionat.dtos.RumDto;
import com.backend1.pensionat.models.Rum;
import com.backend1.pensionat.repos.RumRepo;
import com.backend1.pensionat.services.RumService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RumServiceImpl implements RumService {

    private final RumRepo rumRepo;

    @Override
    public List<RumDto> getAllRum() {
        return rumRepo.findAll().stream().map(r -> rumToRumDto(r)).toList();
    }

    @Override
    public RumDto rumToRumDto(Rum r) {
        return RumDto.builder().id(r.getId()).nummer(r.getNummer()).rumTyp(r.getTyp()).storlek(r.getStorlek()).kapacitet(r.getKapacitet()).pris(r.getPris()).build();
    }

    @Override
    public List<RumDto> getAvailableRum(int antal) {
        switch (antal) {
            case 2: return getAllRum().stream().filter(r -> r.getKapacitet()>1).toList();
            case 3: return getAllRum().stream().filter(r -> r.getKapacitet()>2).toList();
            case 4: return getAllRum().stream().filter(r -> r.getKapacitet()>3).toList();
        }

        return getAllRum();
    }


}
