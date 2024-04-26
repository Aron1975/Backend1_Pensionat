package com.backend1.pensionat.services.impl;


import com.backend1.pensionat.dtos.RumDto;
import com.backend1.pensionat.models.Rum;
import com.backend1.pensionat.repos.RumRepo;
import com.backend1.pensionat.services.RumService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

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
        return RumDto.builder().id(r.getId()).nummer(r.getNummer()).rumTyp(r.getTyp()).storlek(r.getStorlek()).pris(r.getPris()).build();
    }



}
