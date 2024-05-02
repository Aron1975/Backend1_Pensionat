package com.backend1.pensionat.services.impl;

import com.backend1.pensionat.dtos.BokningDto;
import com.backend1.pensionat.dtos.DetailedBokningDto;
import com.backend1.pensionat.dtos.KundDto;
import com.backend1.pensionat.dtos.RumDto;
import com.backend1.pensionat.models.Bokning;
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
    public void deleteBokningWithoutKundId() {
        List<Bokning> bokningar = bokningRepo.findAll();
        for (Bokning bokning : bokningar) {
            if (bokning.getKund() == null) {
                bokningRepo.delete(bokning);
            }
        }
    }

    @Override
    public List<BokningDto> getAllBokningar2() {
        return bokningRepo.findAll().stream().map(b -> bokningToBokningDto(b)).toList();
    }

    @Override
    public BokningDto bokningToBokningDto(Bokning b) {
        return BokningDto.builder().id(b.getId()).bokningsDatum(b.getBokningsDatum()).startDatum(b.getStartDatum())
                .slutDatum(b.getSlutDatum()).antalGäster(b.getAntalGäster()).antalExtraSängar(b.getAntalExtraSängar()).totalPris(b.getTotalPris())
                .rum(new RumDto(b.getRum().getId(), b.getRum().getTyp(),b.getRum().getPris(), b.getRum().getStorlek(), b.getRum().getKapacitet(), b.getRum().getNummer())).build();

    }

    /*@Override
    public void deleteNullBokning(Bokning b) {

    }*/

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
        //List<DetailedBokningDto> allaBokningar = getAllBokningar();
        List<BokningDto> allaBokningar = getAllBokningar2();
        //List<Long> bookedRumIds = allaBokningar.stream().map(i -> i.getRum().getId()).toList();
        boolean isBooked = false;
        if (getAllBokningar2().isEmpty()) {
            availableRumByDate = availableRumByCapacity;
        }
        else {
            for (RumDto r : availableRumByCapacity) {
                for (BokningDto b : allaBokningar) {
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

    /*@Override
    public void deleteNullBokning(){
        List<Bokning> nullBokningar = bokningRepo.getNullBokning();

        for(Bokning bokning : nullBokningar){
            bokningRepo.delete(bokning);
        }
    }*/

    @Override
    public void sparaBokning(DetailedBokningDto b){
        //Bokning bokning = detailedBokningDtoToBokning(b);
       // bokningRepo.save(bokning);

    }

    @Override
    public void sparaBokningTillKund(DetailedBokningDto b) {

    }
}
