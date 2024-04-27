package com.backend1.pensionat.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Rum {

    @Id
    @GeneratedValue
    private long id;

    private int nummer;
    private int storlek;
    private String typ;
    private int kapacitet;
    private double pris;

    public Rum(int nummer, int storlek, String typ, int kapacitet, double pris) {
        this.nummer = nummer;
        this.storlek = storlek;
        this.typ = typ;
        this.kapacitet = kapacitet;
        this.pris = pris;
    }

    @OneToMany(mappedBy = "rum")
    private List<Bokning> bokningList = new ArrayList<>();
}
