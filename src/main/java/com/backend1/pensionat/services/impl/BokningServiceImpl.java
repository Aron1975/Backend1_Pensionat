package com.backend1.pensionat.services.impl;

import com.backend1.pensionat.dtos.DetailedBokningDto;
import com.backend1.pensionat.dtos.DetailedKundDto;
import com.backend1.pensionat.dtos.KundDto;
import com.backend1.pensionat.dtos.RumDto;
import com.backend1.pensionat.models.Bokning;
import com.backend1.pensionat.models.Kund;
import com.backend1.pensionat.models.Rum;
import com.backend1.pensionat.repos.BokningRepo;
import com.backend1.pensionat.services.BokningService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BokningServiceImpl implements BokningService {

    private final BokningRepo bokningRepo;

    @Override
    public List<DetailedBokningDto> getAllBokningar() {
        return bokningRepo.findAll().stream().map(b -> bokningToDetailedBokningDto(b)).toList();
    }

    @Override
    public DetailedBokningDto bokningToDetailedBokningDto(Bokning b) {
        return DetailedBokningDto.builder().id(b.getId()).bokningsDatum(b.getBokningsDatum()).startDatum(b.getStartDatum())
                .slutDatum(b.getSlutDatum()).antalGäster(b.getAntalGäster()).antalExtraSängar(b.getAntalExtraSängar())
                .totalPris(b.getTotalPris()).kund(new KundDto(b.getKund().getId(), b.getKund().getSsn(), b.getKund().getFörnamn(), b.getKund().getEfternamn()))
                .rum(new RumDto(b.getRum().getId(), b.getRum().getTyp(),b.getRum().getPris(), b.getRum().getStorlek(), b.getRum().getKapacitet(), b.getRum().getNummer())).build();

    }

    @Override
    public List<RumDto> getAvailableRumByDate(List<RumDto> availableRumByCapacity, LocalDate startDate, LocalDate stopDate) {
        List<RumDto> availableRumByDate = new ArrayList<>();
        List<DetailedBokningDto> allaBokningar = getAllBokningar();
        //List<Long> bookedRumIds = allaBokningar.stream().map(i -> i.getRum().getId()).toList();
        boolean isBooked = false;
        if (getAllBokningar().isEmpty()) {
            availableRumByDate = availableRumByCapacity;
        }
        else {
            for (RumDto r : availableRumByCapacity) {
                for (DetailedBokningDto b : allaBokningar) {
                    if (r.getId() == b.getRum().getId()) {
                        if ((startDate.isBefore(b.getSlutDatum()) && stopDate.isAfter(b.getStartDatum()))) {
                            isBooked = true;
                        }
                    }
                }
                if (!isBooked) {
                    availableRumByDate.add(r);
                }
                isBooked = false;
            }
        }
        return availableRumByDate;
    }

    @Override
    public void sparaBokning(DetailedBokningDto b){
        //Bokning bokning = detailedBokningDtoToBokning(b);
       // bokningRepo.save(bokning);

    }
}