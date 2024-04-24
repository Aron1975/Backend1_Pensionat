package com.backend1.pensionat.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
public class Bokning {

    @Id
    @GeneratedValue
    private long id;
    private LocalDate bokningsDatum;
    private LocalDate startDatum;
    private LocalDate slutDatum;
    private int antalGäster;
    private int antalExtraSängar;
    private double totalPris;

    @ManyToOne
    @JoinColumn
    private Kund kund;

    @ManyToOne
    @JoinColumn
    private Rum rum;

}
