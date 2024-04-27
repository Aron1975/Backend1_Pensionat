package com.backend1.pensionat.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DetailedBokningDto {

    private long id;
    private LocalDate bokningsDatum;
    private LocalDate startDatum;
    private LocalDate slutDatum;
    private int antalGäster;
    private int antalExtraSängar;
    private double totalPris;
    private KundDto kund;
    private RumDto rum;
}
