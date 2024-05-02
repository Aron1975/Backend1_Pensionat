package com.backend1.pensionat.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Bokning {

    @Id
    @GeneratedValue
    private long id;

    @NotNull(message = "Bokningsdatum får inte vara null.")
    //@FutureOrPresent(message = "Bokningsdatum måste vara idag eller i framtiden.")
    @Column(nullable = false)
    private LocalDate bokningsDatum;

    @NotNull(message = "Startdatum får inte vara null.")
    @FutureOrPresent(message = "Startdatum måste vara idag eller i framtiden.")
    @Column(nullable = false)
    private LocalDate startDatum;

    @NotNull(message = "Slutdatum får inte vara null.")
    @Column(nullable = false)
    private LocalDate slutDatum;

    @Min(value = 1, message = "Antal gäster måste vara minst 1.")
    private int antalGäster;

    @Min(value = 0, message = "Antal extrasängar kan inte vara mindre än 0.")
    private int antalExtraSängar;

    @Positive(message = "Totalpriset måste vara positivt.")
    @Column(nullable = false)
    private double totalPris;

    @ManyToOne
  //  @JoinColumn
    private Kund kund;

    @ManyToOne
  //  @JoinColumn
    private Rum rum;

}
