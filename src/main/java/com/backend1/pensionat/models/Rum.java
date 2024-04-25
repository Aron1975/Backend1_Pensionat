package com.backend1.pensionat.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class Rum {

    @Id
    @GeneratedValue
    private long id;
    private int nummer;
    private int storlek;
    private String typ;
    private double pris;

    public Rum(int nummer, int storlek, String typ, double pris) {
        this.nummer = nummer;
        this.storlek = storlek;
        this.typ = typ;
        this.pris = pris;
    }

    @OneToMany(mappedBy = "rum")
    private List<Bokning> bokningList = new ArrayList<>();
}
